package nl.hyper42.kim.backend.dao;

import com.google.common.io.BaseEncoding;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HLFUserTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testHLFUser() throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
        KeyPair generateKeyPair = instance.generateKeyPair();
        PrivateKey private1 = generateKeyPair.getPrivate();
        String public1 = new String(generateKeyPair.getPublic().getEncoded());

        public1 = BaseEncoding.base64().encode(generateKeyPair.getPublic().getEncoded());
        HLFUserEnrollment enrollment = new HLFUserEnrollment(private1, public1);

        HLFUser user = new HLFUser("someuser", "ebpi-node1", "ebpi-node1MSP", enrollment);

        Assert.assertEquals("someuser", user.getName());
        Assert.assertEquals("ebpi-node1", user.getAffiliation());
        Assert.assertEquals("ebpi-node1MSP", user.getMspId());
        Assert.assertEquals(enrollment, user.getEnrollment());
        Assert.assertNull(user.getAccount());
        Assert.assertNull(user.getRoles());
        Assert.assertNotNull(user.toString());
    }

}
