package nl.hyper42.kim.backend.claim;

import org.junit.Assert;
import org.junit.Test;

public class ClaimCodesTest {

    @Test
    public void TestName() {
        Assert.assertEquals("OlderEightteen", ClaimCodes.OlderEightteen.name());
        Assert.assertEquals("OlderTwentyOne", ClaimCodes.OlderTwentyOne.name());
    }

    @Test
    public void TestValues() {
        Assert.assertEquals(2, ClaimCodes.values().length);
    }
}
