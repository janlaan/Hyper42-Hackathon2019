package nl.hyper42.kim.backend.dao;

import java.security.PrivateKey;
import org.hyperledger.fabric.sdk.Enrollment;

/**
 * The Class HLFUserEnrollment is the basic implementation for {@link Enrollment}
 *
 * @author Micha Wensveen
 */
public class HLFUserEnrollment implements Enrollment {

    /**
     * Field private key
     */
    private final PrivateKey key;
    /**
     * Field certificate
     */
    private final String cert;

    /**
     * Instantiates a new UserEnrollment.
     *
     * @param key - PrivateKey
     * @param cert - String
     */
    public HLFUserEnrollment(PrivateKey key, String cert) {
        super();
        this.key = key;
        this.cert = cert;
    }

    @Override
    public PrivateKey getKey() {
        return key;
    }

    @Override
    public String getCert() {
        return cert;
    }

}
