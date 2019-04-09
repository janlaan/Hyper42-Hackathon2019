
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Individual implements Serializable {

    private final static long serialVersionUID = -8746659722483934896L;
    @SerializedName("gin")
    @Expose
    private Integer gin;
    @SerializedName("cin")
    @Expose
    private Integer cin;

    public Integer getGin() {
        return gin;
    }

    public void setGin(Integer gin) {
        this.gin = gin;
    }

    public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

}
