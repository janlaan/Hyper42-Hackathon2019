package nl.hyper42.kim.backend.dao;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import javax.annotation.ParametersAreNonnullByDefault;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

/**
 * The CredentialsParser is used to get the credentials (private and public key) of a user.
 *
 * @author Micha Wensveen
 */
@ParametersAreNonnullByDefault
public class CredentialsParser {

    /**
     * field cryptoPrimitives
     */
    private CryptoPrimitives cryptoPrimitives;

    /**
     * field credential location certificate
     */
    private final String credentialLocationCertificate;

    /**
     * field credential location private key
     */
    private final String credentialLocationPrivatekey;

    /**
     * Instantiates a new CredentialsParser.
     *
     * @param credentialLocationCertificate certificate location
     * @param credentialLocationPrivatekey private key location
     */
    public CredentialsParser(String credentialLocationCertificate, String credentialLocationPrivatekey) {
        this.credentialLocationCertificate = credentialLocationCertificate;
        this.credentialLocationPrivatekey = credentialLocationPrivatekey;
        try {
            cryptoPrimitives = new CryptoPrimitives();
            cryptoPrimitives.init();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | CryptoException | InvalidArgumentException e) {
            throw new HLFRuntimeException("Problem creating a CredentialParser", e);
        }
    }

    /**
     * Get the private key.
     *
     * @return {@link PrivateKey}
     */
    public PrivateKey getPrivateKey() {
        try {
            byte[] bytes = readFile(credentialLocationPrivatekey);
            return cryptoPrimitives.bytesToPrivateKey(bytes);
        } catch (IOException | CryptoException | IllegalArgumentException e) {
            throw new HLFRuntimeException("Problem parsing private key", e);
        }
    }

    private byte[] readFile(String fileName) throws IOException {
        File pkFile = new File(fileName);
        ByteSource source = Files.asByteSource(pkFile);
        return source.read();
    }

    /**
     * Get the public key.
     *
     * @return the public key as String.
     */
    public String getCertificate() {
        try {
            byte[] bytes = readFile(credentialLocationCertificate);
            return new String(bytes);
        } catch (IOException | IllegalArgumentException e) {
            throw new HLFRuntimeException("Problem parsing certificate", e);
        }
    }

}
