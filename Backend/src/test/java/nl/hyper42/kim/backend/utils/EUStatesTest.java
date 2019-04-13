package nl.hyper42.kim.backend.utils;

import org.junit.Assert;
import org.junit.Test;

public class EUStatesTest {

    @Test
    public void testIsInEu() {
        EUStates euState = EUStates.valueOf("Netherlands");
        Assert.assertEquals(EUStates.Netherlands, euState);
    }

    @Test
    public void testIsNotInEu() {
        try {
            EUStates.valueOf("Norway");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            // this is expected
        }
    }
}
