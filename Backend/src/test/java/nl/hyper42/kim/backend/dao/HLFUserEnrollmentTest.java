package nl.hyper42.kim.backend.dao;

import com.google.common.io.BaseEncoding;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import org.junit.Assert;
import org.junit.Test;

public class HLFUserEnrollmentTest {

    @Test
    public void testGetKey() throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
        KeyPair generateKeyPair = instance.generateKeyPair();
        PrivateKey private1 = generateKeyPair.getPrivate();
        String public1 = new String(generateKeyPair.getPublic().getEncoded());

        public1 = BaseEncoding.base64().encode(generateKeyPair.getPublic().getEncoded());
        HLFUserEnrollment enrollment = new HLFUserEnrollment(private1, public1);

        Assert.assertEquals(private1, enrollment.getKey());
        Assert.assertEquals(public1, enrollment.getCert());
    }

}
