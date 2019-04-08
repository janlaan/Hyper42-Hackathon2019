
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XpCounter implements Serializable
{

    @SerializedName("labelXP")
    @Expose
    private String labelXP;
    @SerializedName("xpAmount")
    @Expose
    private Integer xpAmount;
    @SerializedName("labelEndOfQualifyingPeriod")
    @Expose
    private String labelEndOfQualifyingPeriod;
    @SerializedName("endOfQualifyingPeriod")
    @Expose
    private String endOfQualifyingPeriod;
    @SerializedName("ultimate")
    @Expose
    private Boolean ultimate;
    private final static long serialVersionUID = 6883504504246229760L;

    public String getLabelXP() {
        return labelXP;
    }

    public void setLabelXP(String labelXP) {
        this.labelXP = labelXP;
    }

    public Integer getXpAmount() {
        return xpAmount;
    }

    public void setXpAmount(Integer xpAmount) {
        this.xpAmount = xpAmount;
    }

    public String getLabelEndOfQualifyingPeriod() {
        return labelEndOfQualifyingPeriod;
    }

    public void setLabelEndOfQualifyingPeriod(String labelEndOfQualifyingPeriod) {
        this.labelEndOfQualifyingPeriod = labelEndOfQualifyingPeriod;
    }

    public String getEndOfQualifyingPeriod() {
        return endOfQualifyingPeriod;
    }

    public void setEndOfQualifyingPeriod(String endOfQualifyingPeriod) {
        this.endOfQualifyingPeriod = endOfQualifyingPeriod;
    }

    public Boolean getUltimate() {
        return ultimate;
    }

    public void setUltimate(Boolean ultimate) {
        this.ultimate = ultimate;
    }

}
