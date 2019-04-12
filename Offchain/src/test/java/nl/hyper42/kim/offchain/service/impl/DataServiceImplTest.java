package nl.hyper42.kim.offchain.service.impl;

import java.util.Optional;
import nl.hyper42.kim.offchain.dao.DataDao;
import nl.hyper42.kim.offchain.dao.SequenceDao;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;
import nl.hyper42.kim.offchain.models.DataDto;
import nl.hyper42.kim.offchain.models.DataHashDto;
import nl.hyper42.kim.offchain.rest.DataControllerTestData;
import nl.hyper42.kim.offchain.service.DataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DataServiceImplTest {

    private static final String ID = "some location";

    @Mock
    DataDao dataDao;

    @Mock
    SequenceDao sequenceDao;

    @InjectMocks
    DataService dataService = new DataServiceImpl();

    @Captor
    ArgumentCaptor<DataDto> dtoCaptor = ArgumentCaptor.forClass(DataDto.class);

    @Captor
    ArgumentCaptor<String> seqNameCapture = ArgumentCaptor.forClass(String.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void submitDataSuccess() {
        TravelData travelData = DataControllerTestData.createTravelData();
        DataDto dto = createDataDto(travelData);
        Mockito.when(dataDao.save(dtoCaptor.capture())).thenReturn(dto);
        Mockito.when(sequenceDao.generateSequence(seqNameCapture.capture())).thenReturn(10L);

        String result = dataService.submitData(travelData);

        DataDto dtoCaptured = dtoCaptor.getValue();
        Assert.assertNotNull(dtoCaptured);
        Assert.assertEquals(travelData.getDataHash(), dtoCaptured.getDataHash().getHash());
        Assert.assertEquals(travelData.getDataHashLocation(), dtoCaptured.getDataHash().getAddress());
        Assert.assertEquals(travelData.getDataHashSalt(), dtoCaptured.getDataHash().getSalt());
        Assert.assertEquals(travelData.getPhoto(), new String(dtoCaptured.getPhoto()));
        Assert.assertEquals(travelData.getClaimAddresses(), dtoCaptured.getClaimAddresses());
        Assert.assertEquals("10", dtoCaptured.getId());

        Assert.assertEquals("some location", result);
    }

    @Test
    public void gatherDataByIdSuccess() {
        TravelData travelData = DataControllerTestData.createTravelData();

        DataDto dto = createDataDto(travelData);
        Mockito.when(dataDao.findById(ID)).thenReturn(Optional.of(dto));
        Optional<TravelData> result = dataService.gatherDataById(ID);

        Assert.assertTrue(result.isPresent());
        TravelData data = result.get();
        Assert.assertEquals(dto.getClaimAddresses(), data.getClaimAddresses());
        Assert.assertArrayEquals(dto.getPhoto(), data.getPhoto().getBytes());
        Assert.assertEquals(dto.getDataHash().getAddress(), data.getDataHashLocation());
        Assert.assertEquals(dto.getDataHash().getHash(), data.getDataHash());
        Assert.assertEquals(dto.getDataHash().getSalt(), data.getDataHashSalt());
    }

    @Test
    public void gatherDataByIdNotFound() {
        Mockito.when(dataDao.findById(ID)).thenReturn(Optional.empty());
        Optional<TravelData> result = dataService.gatherDataById(ID);

        Assert.assertTrue(result.isEmpty());
    }

    private DataDto createDataDto(TravelData travelData) {
        DataDto dataDto = new DataDto();
        dataDto.setId(ID);
        dataDto.setClaimAddresses(travelData.getClaimAddresses());
        dataDto.setPhoto(travelData.getPhoto().getBytes());
        DataHashDto dataHashDto = new DataHashDto();
        dataHashDto.setAddress(travelData.getDataHashLocation());
        dataHashDto.setHash(travelData.getDataHash());
        dataHashDto.setSalt(travelData.getDataHashSalt());
        dataDto.setDataHash(dataHashDto);

        return dataDto;
    }
}
