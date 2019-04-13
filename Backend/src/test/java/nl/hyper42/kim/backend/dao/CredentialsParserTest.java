package nl.hyper42.kim.backend.dao;

import java.security.PrivateKey;
import org.junit.Assert;
import org.junit.Test;

public class CredentialsParserTest {

    @Test
    public void testGetPrivateKey() {
        CredentialsParser credentialsParser =
                new CredentialsParser("target/test-classes/credentials/user1/certificate", "target/test-classes/credentials/user1/privateKey");
        PrivateKey privateKey = credentialsParser.getPrivateKey();
        Assert.assertNotNull(privateKey);
    }

    @Test
    public void testGetCertificate() {
        CredentialsParser credentialsParser =
                new CredentialsParser("target/test-classes/credentials/user1/certificate", "target/test-classes/credentials/user1/privateKey");
        String certificate = credentialsParser.getCertificate();
        Assert.assertNotNull(certificate);
    }

    @Test(expected = HLFRuntimeException.class)
    public void testGetPrivateKeyNotFound() {
        CredentialsParser credentialsParser =
                new CredentialsParser("target/test-classes/credentials/user1/certificate", "target/test-classes/credentials/user1/privateKeyNotFound");
        credentialsParser.getPrivateKey();
    }

    @Test(expected = HLFRuntimeException.class)
    public void testGetCertificateNotFound() {
        CredentialsParser credentialsParser =
                new CredentialsParser("target/test-classes/credentials/user1/certificateNotFound", "target/test-classes/credentials/user1/privateKey");
        credentialsParser.getCertificate();
    }

}
