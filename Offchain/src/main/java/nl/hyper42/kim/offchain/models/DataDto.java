package nl.hyper42.kim.offchain.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The Document to store on the MongoDB.
 *
 * @author Micha Wensveen
 */
@SuppressWarnings("PMD.DataClass")
@Document(collection = "Data")
public final class DataDto {

    /** Id as String. */
    @Id
    @Field("_id")
    private String id;

    /**
     * Data about the Hash.
     */
    @Field("hash")
    private DataHashDto dataHash;

    /** The claim addresses. */
    @Field("claimAddresses")
    private List<String> claimAddresses;

    /** The photo. */
    @Field("photo")
    private byte[] photo;

    /**
     * Getter.
     *
     * @return {@link nl.hyper42.kim.offchain.models.DataDto#dataHash}
     */
    public DataHashDto getDataHash() {
        return dataHash;
    }

    /**
     * Setter.
     *
     * @param dataHash {@link nl.hyper42.kim.offchain.models.DataDto#dataHash}
     */
    public void setDataHash(DataHashDto dataHash) {
        this.dataHash = dataHash;
    }

    /**
     * Getter.
     *
     * @return {@link nl.hyper42.kim.offchain.models.DataDto#claimAddresses}
     */
    public List<String> getClaimAddresses() {
        return claimAddresses;
    }

    /**
     * Setter.
     *
     * @param claimAddresses {@link nl.hyper42.kim.offchain.models.DataDto#claimAddresses}
     */
    public void setClaimAddresses(List<String> claimAddresses) {
        this.claimAddresses = claimAddresses;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}