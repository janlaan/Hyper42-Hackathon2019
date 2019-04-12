package nl.hyper42.kim.offchain.rest;

import java.util.Optional;
import nl.hyper42.kim.offchain.model.generated.api.ResultID;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;
import nl.hyper42.kim.offchain.service.DataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataControllerTest {

    @Mock
    DataService dataService;

    @InjectMocks
    DataController dataController = new DataController();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gatherDataSuccessTest() {
        Mockito.when(dataService.gatherDataById(DataControllerTestData.ID)).thenReturn(Optional.of(DataControllerTestData.createTravelData()));
        ResponseEntity<TravelData> result = dataController.searchTravelDataById(DataControllerTestData.ID);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        TravelData travelData = result.getBody();
        Assert.assertEquals(DataControllerTestData.ADDRESSES, travelData.getClaimAddresses());
        Assert.assertEquals(DataControllerTestData.PHOTO, travelData.getPhoto());
        Assert.assertEquals(DataControllerTestData.DATA_HASH, travelData.getDataHash());
        Assert.assertEquals(DataControllerTestData.DATA_HASH_LOCATION, travelData.getDataHashLocation());
        Assert.assertEquals(DataControllerTestData.DATA_HASH_SALT, travelData.getDataHashSalt());
    }

    @Test
    public void gatherDataNotFoundTest() {
        Mockito.when(dataService.gatherDataById(DataControllerTestData.ID)).thenReturn(Optional.empty());
        ResponseEntity<TravelData> result = dataController.searchTravelDataById(DataControllerTestData.ID);

        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assert.assertNull(result.getBody());
    }

    @Test
    public void submitDataSuccessTest() {
        TravelData travelData = DataControllerTestData.createTravelData();

        Mockito.when(dataService.submitData(travelData)).thenReturn(DataControllerTestData.ID);
        ResponseEntity<ResultID> result = dataController.submitData(travelData);

        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assert.assertNotNull(result.getBody());
        Assert.assertEquals(DataControllerTestData.ID, result.getBody().getId());
    }

}
