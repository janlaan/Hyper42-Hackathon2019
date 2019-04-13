package nl.hyper42.kim.backend.rest;

import java.util.Arrays;
import java.util.List;
import nl.hyper42.kim.backend.claim.ClaimCodes;
import nl.hyper42.kim.backend.model.generated.api.Authorisation;
import nl.hyper42.kim.backend.model.generated.api.TravelDataRequest;
import nl.hyper42.kim.backend.model.generated.api.TravelDataResponse;
import nl.hyper42.kim.backend.service.BackendService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class BackendControllerTest {
    @Mock
    private BackendService backendService;
    @InjectMocks
    private BackendController backendController;

    @Captor
    private ArgumentCaptor<TravelDataRequest> dataCapture;

    @Test
    public void testSubmitData() throws Exception {
        TravelDataResponse response = new TravelDataResponse();
        Mockito.when(backendService.submitData(dataCapture.capture())).thenReturn(response);

        String passportData =
                "{\"name\": \"MyName\", \"DateOfBirth\": \"1999-04-12\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC18 = new Authorisation().withClaimName(ClaimCodes.OlderEightteen.name()).withWho(Arrays.asList("KLM", "Tranavia"))
                .withWhere(Arrays.asList("AMS", "BRU")).withRole(Arrays.asList("Lounge", "Shop"));
        Authorisation aC21 = new Authorisation().withClaimName(ClaimCodes.OlderTwentyOne.name()).withWho(Arrays.asList("KLM2", "Tranavia2"))
                .withWhere(Arrays.asList("AMS2", "BRU2")).withRole(Arrays.asList("Lounge2", "Shop2"));
        List<Authorisation> authorisations = Arrays.asList(aC18, aC21);
        TravelDataRequest dataRequest =
                new TravelDataRequest().withPhoto("1234").withPassportData(passportData).withTravelData(travelData).withAuthorisation(authorisations);

        ResponseEntity<TravelDataResponse> responseEntity = backendController.submitData(dataRequest);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals(response, responseEntity.getBody());

    }

}
