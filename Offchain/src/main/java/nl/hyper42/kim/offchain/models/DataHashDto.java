package nl.hyper42.kim.offchain.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Document embedded in to DataDTO document.
 * 
 * @author Micha Wensveen
 */
@SuppressWarnings("PMD.DataClass")
@Document(collection = "Data")
public final class DataHashDto {

    /** Id as String. */
    @Field("address")
    private String address;

    /** hash as String. */
    @Field("hash")
    private String hash;

    @Field("salt")
    private String salt;

    /**
     * Getter.
     *
     * @return {@link nl.hyper42.kim.offchain.models.DataHashDto#address}
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter.
     *
     * @param address {@link nl.hyper42.kim.offchain.models.DataHashDto#address}
     */
    public void setAddress(String id) {
        this.address = id;
    }

    /**
     * Getter.
     *
     * @return {@link nl.hyper42.kim.offchain.models.DataHashDto#hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Setter.
     *
     * @param hash {@link nl.hyper42.kim.offchain.models.DataHashDto#hash}
     */
    public void setHash(String data) {
        this.hash = data;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}