package nl.hyper42.kim.backend.dao;

import java.util.Set;
import javax.annotation.ParametersAreNonnullByDefault;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

/**
 * Basic implementation of the {@link User} interface.
 */
@ParametersAreNonnullByDefault
public class HLFUser implements User {
    /**
     * field name
     */
    private final String name;
    /**
     * field roles as Set
     */
    private Set<String> roles;
    /**
     * field account
     */
    private String account;
    /**
     * field affiliation
     */
    private final String affiliation;
    /**
     * field enrollment
     */
    private final Enrollment enrollment;
    /**
     * field message id
     */
    private final String mspId;

    /**
     * Instantiates a new HLF user.
     *
     * @param name the name
     * @param affiliation the affiliation
     * @param mspId the msp id
     * @param enrollment the enrollment
     */
    public HLFUser(String name, String affiliation, String mspId, Enrollment enrollment) {
        this.name = name;
        this.affiliation = affiliation;
        this.enrollment = enrollment;
        this.mspId = mspId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getAffiliation() {
        return affiliation;
    }

    @Override
    public Enrollment getEnrollment() {
        return enrollment;
    }

    @Override
    public String getMspId() {
        return mspId;
    }

    @Override
    public String toString() {
        return "HLFUser{" + "name='" + name + '\'' + "\n, roles=" + roles + "\n, account='" + account + '\'' + "\n, affiliation='" + affiliation + '\''
                + "\n, enrollment=" + enrollment + "\n, mspId='" + mspId + '\'' + '}';
    }
}
