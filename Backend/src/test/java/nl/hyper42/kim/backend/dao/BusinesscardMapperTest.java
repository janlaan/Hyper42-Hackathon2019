package nl.hyper42.kim.backend.dao;

import org.hyperledger.fabric.sdk.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BusinesscardMapperTest {

    @Test
    public void testBusinesscardMapper() {
        BusinesscardMapper businesscardMapper = new BusinesscardMapper("user1", "target/test-classes/credentials/user1/certificate",
                "target/test-classes/credentials/user1/privateKey", "affil1", "msp1");
        User user = businesscardMapper.getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals("user1", user.getName());
        Assert.assertEquals("affil1", user.getAffiliation());
        Assert.assertEquals("msp1", user.getMspId());
    }
}
