package nl.hyper42.kim.backend.dao;

import java.security.PrivateKey;
import javax.annotation.ParametersAreNonnullByDefault;
import org.hyperledger.fabric.sdk.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BusinesscardMapper helps to create a {@link User} based on info on the filesystem.
 *
 * @author Micha Wensveen
 */
@ParametersAreNonnullByDefault
class BusinesscardMapper {

    /**
     * instantiates logging for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(BusinesscardMapper.class);

    /**
     * cardname declared as string
     */
    private final String cardName;

    /**
     * field credential certificate location
     */
    private final String credentialLocationCertificate;

    /**
     * field credential private key location
     */
    private final String credentialLocationPrivatekey;

    /** The user affiliation. */
    private final String userAffiliation;

    /** The user msp id. */
    private final String userMspId;

    /**
     * Instantiates a new BusinesscardMapper.
     *
     * @param cardName - Name of the user to load.
     * @param credentialLocationCertificate Location of the certificate
     * @param credentialLocationPrivatekey Location of the private key
     * @param userAffiliation - affiliation of the user
     * @param userMspId - the id of the msp of the user
     */
    public BusinesscardMapper(String cardName, String credentialLocationCertificate, String credentialLocationPrivatekey, String userAffiliation,
            String userMspId) {
        this.cardName = cardName;
        this.credentialLocationCertificate = credentialLocationCertificate;
        this.credentialLocationPrivatekey = credentialLocationPrivatekey;
        this.userAffiliation = userAffiliation;
        this.userMspId = userMspId;
    }

    /**
     * Create the {@link User}.
     *
     * @return {@link be.vo.psn.blockchain.backend.information.hlf.utils.BusinesscardMapper#user}
     */
    public User getUser() {
        LOG.debug("Mapping user {}", cardName);

        CredentialsParser credentialsParser = new CredentialsParser(credentialLocationCertificate, credentialLocationPrivatekey);

        PrivateKey privateKey = credentialsParser.getPrivateKey();
        String certificate = credentialsParser.getCertificate();
        return new HLFUser(cardName, userAffiliation, userMspId, new HLFUserEnrollment(privateKey, certificate));
    }
}
