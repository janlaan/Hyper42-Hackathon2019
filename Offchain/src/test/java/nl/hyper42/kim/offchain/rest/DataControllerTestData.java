package nl.hyper42.kim.offchain.rest;

import java.util.Arrays;
import java.util.List;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;

public class DataControllerTestData {

    static final String ID = "some ID";
    static final String PHOTO = "some Photo data";
    static final String DATA_HASH = "some data hash";
    static final String DATA_HASH_SALT = "some data hash Salt";
    static final String DATA_HASH_LOCATION = "some data hash Location";
    static final List<String> ADDRESSES = Arrays.asList("addressOne", "addressTwo");

    public static TravelData createTravelData() {
        return new TravelData().withClaimAddresses(ADDRESSES).withDataHash(DATA_HASH).withDataHashLocation(DATA_HASH_LOCATION).withDataHashSalt(DATA_HASH_SALT)
                .withPhoto(PHOTO);
    }
}
