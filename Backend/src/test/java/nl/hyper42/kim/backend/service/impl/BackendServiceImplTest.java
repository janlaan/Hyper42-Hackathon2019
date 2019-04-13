package nl.hyper42.kim.backend.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import nl.hyper42.kim.backend.claim.ClaimCodes;
import nl.hyper42.kim.backend.dao.HLFInvoker;
import nl.hyper42.kim.backend.model.generated.api.Authorisation;
import nl.hyper42.kim.backend.model.generated.api.ClaimAddress;
import nl.hyper42.kim.backend.model.generated.api.TravelDataRequest;
import nl.hyper42.kim.backend.model.generated.api.TravelDataResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BackendServiceImplTest {
    @Mock
    private HLFInvoker hlfInvoker;
    @InjectMocks
    private BackendServiceImpl backendServiceImpl;
    @Captor
    private ArgumentCaptor<String> methodeCapture = ArgumentCaptor.forClass(String.class);
    private ArgumentCaptor<String> argsCapture = ArgumentCaptor.forClass(String.class);

    @Test
    public void testSubmitData() throws Exception {
        Mockito.when(hlfInvoker.invokeChaincode(methodeCapture.capture(), argsCapture.capture())).thenReturn("bla");

        String passportData =
                "{\"name\": \"MyName\", \"DateOfBirth\": \"1998-04-12\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC18 = new Authorisation().withClaimName(ClaimCodes.OlderEightteen.name()).withWho(Arrays.asList("KLM", "Transavia"))
                .withWhere(Arrays.asList("AMS", "BRU")).withRole(Arrays.asList("Lounge", "Shop"));
        Authorisation aC21 = new Authorisation().withClaimName(ClaimCodes.OlderTwentyOne.name()).withWho(Arrays.asList("KLM2", "Transavia2"))
                .withWhere(Arrays.asList("AMS2", "BRU2")).withRole(Arrays.asList("Lounge2", "Shop2"));
        List<Authorisation> authorisations = Arrays.asList(aC18, aC21);
        TravelDataRequest dataRequest = new TravelDataRequest().withPhoto(Base64.getEncoder().encodeToString("1234".getBytes())).withPassportData(passportData)
                .withTravelData(travelData).withAuthorisation(authorisations);

        TravelDataResponse travelDataResponse = backendServiceImpl.submitData(dataRequest);

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(0));
        Assert.assertNotNull(argsCapture.getAllValues().get(0));
        Assert.assertEquals("OlderEightteen", argsCapture.getAllValues().get(1));
        Assert.assertEquals("true", argsCapture.getAllValues().get(2));
        Assert.assertEquals("KLM,Transavia", argsCapture.getAllValues().get(3));
        Assert.assertEquals("AMS,BRU", argsCapture.getAllValues().get(4));
        Assert.assertEquals("Lounge,Shop", argsCapture.getAllValues().get(5));

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(1));
        Assert.assertNotNull(argsCapture.getAllValues().get(6));
        Assert.assertEquals("OlderTwentyOne", argsCapture.getAllValues().get(7));
        Assert.assertEquals("true", argsCapture.getAllValues().get(8));
        Assert.assertEquals("KLM2,Transavia2", argsCapture.getAllValues().get(9));
        Assert.assertEquals("AMS2,BRU2", argsCapture.getAllValues().get(10));
        Assert.assertEquals("Lounge2,Shop2", argsCapture.getAllValues().get(11));

        Assert.assertEquals("storeProfilePic", methodeCapture.getAllValues().get(2));
        Assert.assertNotNull(argsCapture.getAllValues().get(12));
        Assert.assertEquals(Base64.getEncoder().encodeToString("34".getBytes()), argsCapture.getAllValues().get(13));

        Assert.assertEquals("registerHash", methodeCapture.getAllValues().get(3));
        Assert.assertNotNull(argsCapture.getAllValues().get(14));
        Assert.assertNotNull(argsCapture.getAllValues().get(15));

        Assert.assertNotNull(travelDataResponse.getDataHashAddress());
        Assert.assertNotNull(travelDataResponse.getDataHash());
        Assert.assertEquals(2, travelDataResponse.getClaimAddresses().size());
        List<ClaimAddress> claimAddresses = travelDataResponse.getClaimAddresses();
        Assert.assertNotNull(claimAddresses.get(0).getClaimAddress());
        Assert.assertEquals("OlderTwentyOne", claimAddresses.get(0).getClaimName());
        Assert.assertNotNull(claimAddresses.get(1).getClaimAddress());
        Assert.assertEquals("OlderEightteen", claimAddresses.get(1).getClaimName());
        Assert.assertNotNull(travelDataResponse.getPhotoAddress());
        Assert.assertEquals("MzQ=", travelDataResponse.getPhotoKey());
    }

    @Test
    public void testSubmitDataNot21() throws Exception {
        Mockito.when(hlfInvoker.invokeChaincode(methodeCapture.capture(), argsCapture.capture())).thenReturn("bla");
        int year = LocalDate.now().getYear() - 20;
        String dob = Integer.toString(year) + "-01-01";
        String passportData = "{\"name\": \"MyName\", \"DateOfBirth\": \"" + dob
                + "\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC18 = new Authorisation().withClaimName(ClaimCodes.OlderEightteen.name()).withWho(Arrays.asList("KLM", "Transavia"))
                .withWhere(Arrays.asList("AMS", "BRU")).withRole(Arrays.asList("Lounge", "Shop"));
        Authorisation aC21 = new Authorisation().withClaimName(ClaimCodes.OlderTwentyOne.name()).withWho(Arrays.asList("KLM2", "Transavia2"))
                .withWhere(Arrays.asList("AMS2", "BRU2")).withRole(Arrays.asList("Lounge2", "Shop2"));
        List<Authorisation> authorisations = Arrays.asList(aC18, aC21);
        TravelDataRequest dataRequest = new TravelDataRequest().withPhoto(Base64.getEncoder().encodeToString("1234".getBytes())).withPassportData(passportData)
                .withTravelData(travelData).withAuthorisation(authorisations);

        backendServiceImpl.submitData(dataRequest);

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(0));
        Assert.assertNotNull(argsCapture.getAllValues().get(0));
        Assert.assertEquals("OlderEightteen", argsCapture.getAllValues().get(1));
        Assert.assertEquals("true", argsCapture.getAllValues().get(2));
        Assert.assertEquals("KLM,Transavia", argsCapture.getAllValues().get(3));
        Assert.assertEquals("AMS,BRU", argsCapture.getAllValues().get(4));
        Assert.assertEquals("Lounge,Shop", argsCapture.getAllValues().get(5));

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(1));
        Assert.assertNotNull(argsCapture.getAllValues().get(6));
        Assert.assertEquals("OlderTwentyOne", argsCapture.getAllValues().get(7));
        Assert.assertEquals("false", argsCapture.getAllValues().get(8));
        Assert.assertEquals("KLM2,Transavia2", argsCapture.getAllValues().get(9));
        Assert.assertEquals("AMS2,BRU2", argsCapture.getAllValues().get(10));
        Assert.assertEquals("Lounge2,Shop2", argsCapture.getAllValues().get(11));

        Assert.assertEquals("storeProfilePic", methodeCapture.getAllValues().get(2));
        Assert.assertNotNull(argsCapture.getAllValues().get(12));
        Assert.assertEquals(Base64.getEncoder().encodeToString("34".getBytes()), argsCapture.getAllValues().get(13));

        Assert.assertEquals("registerHash", methodeCapture.getAllValues().get(3));
        Assert.assertNotNull(argsCapture.getAllValues().get(14));
        Assert.assertNotNull(argsCapture.getAllValues().get(15));
    }

    @Test
    public void testSubmitDataNot18() throws Exception {
        Mockito.when(hlfInvoker.invokeChaincode(methodeCapture.capture(), argsCapture.capture())).thenReturn("bla");
        int year = LocalDate.now().getYear() - 17;
        String dob = Integer.toString(year) + "-01-01";
        String passportData = "{\"name\": \"MyName\", \"DateOfBirth\": \"" + dob
                + "\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC18 = new Authorisation().withClaimName(ClaimCodes.OlderEightteen.name()).withWho(Arrays.asList("KLM", "Transavia"))
                .withWhere(Arrays.asList("AMS", "BRU")).withRole(Arrays.asList("Lounge", "Shop"));
        Authorisation aC21 = new Authorisation().withClaimName(ClaimCodes.OlderTwentyOne.name()).withWho(Arrays.asList("KLM2", "Transavia2"))
                .withWhere(Arrays.asList("AMS2", "BRU2")).withRole(Arrays.asList("Lounge2", "Shop2"));
        List<Authorisation> authorisations = Arrays.asList(aC18, aC21);
        TravelDataRequest dataRequest = new TravelDataRequest().withPhoto(Base64.getEncoder().encodeToString("1234".getBytes())).withPassportData(passportData)
                .withTravelData(travelData).withAuthorisation(authorisations);

        backendServiceImpl.submitData(dataRequest);

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(0));
        Assert.assertNotNull(argsCapture.getAllValues().get(0));
        Assert.assertEquals("OlderEightteen", argsCapture.getAllValues().get(1));
        Assert.assertEquals("false", argsCapture.getAllValues().get(2));
        Assert.assertEquals("KLM,Transavia", argsCapture.getAllValues().get(3));
        Assert.assertEquals("AMS,BRU", argsCapture.getAllValues().get(4));
        Assert.assertEquals("Lounge,Shop", argsCapture.getAllValues().get(5));

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(1));
        Assert.assertNotNull(argsCapture.getAllValues().get(6));
        Assert.assertEquals("OlderTwentyOne", argsCapture.getAllValues().get(7));
        Assert.assertEquals("false", argsCapture.getAllValues().get(8));
        Assert.assertEquals("KLM2,Transavia2", argsCapture.getAllValues().get(9));
        Assert.assertEquals("AMS2,BRU2", argsCapture.getAllValues().get(10));
        Assert.assertEquals("Lounge2,Shop2", argsCapture.getAllValues().get(11));

        Assert.assertEquals("storeProfilePic", methodeCapture.getAllValues().get(2));
        Assert.assertNotNull(argsCapture.getAllValues().get(12));
        Assert.assertEquals(Base64.getEncoder().encodeToString("34".getBytes()), argsCapture.getAllValues().get(13));

        Assert.assertEquals("registerHash", methodeCapture.getAllValues().get(3));
        Assert.assertNotNull(argsCapture.getAllValues().get(14));
        Assert.assertNotNull(argsCapture.getAllValues().get(15));
    }

    @Test
    public void testSubmitDataNoAuthorisation18() throws Exception {
        Mockito.when(hlfInvoker.invokeChaincode(methodeCapture.capture(), argsCapture.capture())).thenReturn("bla");
        int year = LocalDate.now().getYear() - 17;
        String dob = Integer.toString(year) + "-01-01";
        String passportData = "{\"name\": \"MyName\", \"DateOfBirth\": \"" + dob
                + "\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC21 = new Authorisation().withClaimName(ClaimCodes.OlderTwentyOne.name()).withWho(Arrays.asList("KLM2", "Transavia2"))
                .withWhere(Arrays.asList("AMS2", "BRU2")).withRole(Arrays.asList("Lounge2", "Shop2"));
        List<Authorisation> authorisations = Arrays.asList(aC21);
        TravelDataRequest dataRequest = new TravelDataRequest().withPhoto(Base64.getEncoder().encodeToString("1234".getBytes())).withPassportData(passportData)
                .withTravelData(travelData).withAuthorisation(authorisations);

        backendServiceImpl.submitData(dataRequest);

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(0));
        Assert.assertNotNull(argsCapture.getAllValues().get(0));
        Assert.assertEquals("OlderTwentyOne", argsCapture.getAllValues().get(1));
        Assert.assertEquals("false", argsCapture.getAllValues().get(2));
        Assert.assertEquals("KLM2,Transavia2", argsCapture.getAllValues().get(3));
        Assert.assertEquals("AMS2,BRU2", argsCapture.getAllValues().get(4));
        Assert.assertEquals("Lounge2,Shop2", argsCapture.getAllValues().get(5));

        Assert.assertEquals("storeProfilePic", methodeCapture.getAllValues().get(1));
        Assert.assertNotNull(argsCapture.getAllValues().get(6));
        Assert.assertEquals(Base64.getEncoder().encodeToString("34".getBytes()), argsCapture.getAllValues().get(7));

        Assert.assertEquals("registerHash", methodeCapture.getAllValues().get(2));
        Assert.assertNotNull(argsCapture.getAllValues().get(8));
        Assert.assertNotNull(argsCapture.getAllValues().get(9));
    }

    @Test
    public void testSubmitDataNoAuthorisation21() throws Exception {
        Mockito.when(hlfInvoker.invokeChaincode(methodeCapture.capture(), argsCapture.capture())).thenReturn("bla");
        int year = LocalDate.now().getYear() - 20;
        String dob = Integer.toString(year) + "-01-01";
        String passportData = "{\"name\": \"MyName\", \"DateOfBirth\": \"" + dob
                + "\", \"Nationality\": \"Dutch\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "";
        Authorisation aC18 = new Authorisation().withClaimName(ClaimCodes.OlderEightteen.name()).withWho(Arrays.asList("KLM", "Transavia"))
                .withWhere(Arrays.asList("AMS", "BRU")).withRole(Arrays.asList("Lounge", "Shop"));
        List<Authorisation> authorisations = Arrays.asList(aC18);
        TravelDataRequest dataRequest = new TravelDataRequest().withPhoto(Base64.getEncoder().encodeToString("1234".getBytes())).withPassportData(passportData)
                .withTravelData(travelData).withAuthorisation(authorisations);

        backendServiceImpl.submitData(dataRequest);

        Assert.assertEquals("registerClaim", methodeCapture.getAllValues().get(0));
        Assert.assertNotNull(argsCapture.getAllValues().get(0));
        Assert.assertEquals("OlderEightteen", argsCapture.getAllValues().get(1));
        Assert.assertEquals("true", argsCapture.getAllValues().get(2));
        Assert.assertEquals("KLM,Transavia", argsCapture.getAllValues().get(3));
        Assert.assertEquals("AMS,BRU", argsCapture.getAllValues().get(4));
        Assert.assertEquals("Lounge,Shop", argsCapture.getAllValues().get(5));

        Assert.assertEquals("storeProfilePic", methodeCapture.getAllValues().get(1));
        Assert.assertNotNull(argsCapture.getAllValues().get(6));
        Assert.assertEquals(Base64.getEncoder().encodeToString("34".getBytes()), argsCapture.getAllValues().get(7));

        Assert.assertEquals("registerHash", methodeCapture.getAllValues().get(2));
        Assert.assertNotNull(argsCapture.getAllValues().get(8));
        Assert.assertNotNull(argsCapture.getAllValues().get(9));
    }
}
