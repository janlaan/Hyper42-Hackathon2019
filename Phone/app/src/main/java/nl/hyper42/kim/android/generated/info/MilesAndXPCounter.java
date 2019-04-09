
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MilesAndXPCounter implements Serializable {

    private final static long serialVersionUID = -4819598969577234907L;
    @SerializedName("labelMiles")
    @Expose
    private String labelMiles;
    @SerializedName("milesAmount")
    @Expose
    private Integer milesAmount;
    @SerializedName("xpCounters")
    @Expose
    private List<XpCounter> xpCounters = null;
    @SerializedName("xpGlobals")
    @Expose
    private List<XpGlobal> xpGlobals = null;

    public String getLabelMiles() {
        return labelMiles;
    }

    public void setLabelMiles(String labelMiles) {
        this.labelMiles = labelMiles;
    }

    public Integer getMilesAmount() {
        return milesAmount;
    }

    public void setMilesAmount(Integer milesAmount) {
        this.milesAmount = milesAmount;
    }

    public List<XpCounter> getXpCounters() {
        return xpCounters;
    }

    public void setXpCounters(List<XpCounter> xpCounters) {
        this.xpCounters = xpCounters;
    }

    public List<XpGlobal> getXpGlobals() {
        return xpGlobals;
    }

    public void setXpGlobals(List<XpGlobal> xpGlobals) {
        this.xpGlobals = xpGlobals;
    }

}
