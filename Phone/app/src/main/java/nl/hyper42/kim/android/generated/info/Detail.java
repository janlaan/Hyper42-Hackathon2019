
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail implements Serializable {

    private final static long serialVersionUID = -7413794990349623094L;
    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("activityDate")
    @Expose
    private String activityDate;
    @SerializedName("iconURL")
    @Expose
    private String iconURL;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("milesAmount")
    @Expose
    private Integer milesAmount;
    @SerializedName("xpAmount")
    @Expose
    private Integer xpAmount;
    @SerializedName("labelMiles")
    @Expose
    private String labelMiles;
    @SerializedName("labelXP")
    @Expose
    private String labelXP;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("ultimateEligibility")
    @Expose
    private Boolean ultimateEligibility;
    @SerializedName("xpActivityDetails")
    @Expose
    private List<XpActivityDetail> xpActivityDetails = null;
    @SerializedName("complementaryDetailDescriptionData")
    @Expose
    private List<String> complementaryDetailDescriptionData = null;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMilesAmount() {
        return milesAmount;
    }

    public void setMilesAmount(Integer milesAmount) {
        this.milesAmount = milesAmount;
    }

    public Integer getXpAmount() {
        return xpAmount;
    }

    public void setXpAmount(Integer xpAmount) {
        this.xpAmount = xpAmount;
    }

    public String getLabelMiles() {
        return labelMiles;
    }

    public void setLabelMiles(String labelMiles) {
        this.labelMiles = labelMiles;
    }

    public String getLabelXP() {
        return labelXP;
    }

    public void setLabelXP(String labelXP) {
        this.labelXP = labelXP;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Boolean getUltimateEligibility() {
        return ultimateEligibility;
    }

    public void setUltimateEligibility(Boolean ultimateEligibility) {
        this.ultimateEligibility = ultimateEligibility;
    }

    public List<XpActivityDetail> getXpActivityDetails() {
        return xpActivityDetails;
    }

    public void setXpActivityDetails(List<XpActivityDetail> xpActivityDetails) {
        this.xpActivityDetails = xpActivityDetails;
    }

    public List<String> getComplementaryDetailDescriptionData() {
        return complementaryDetailDescriptionData;
    }

    public void setComplementaryDetailDescriptionData(List<String> complementaryDetailDescriptionData) {
        this.complementaryDetailDescriptionData = complementaryDetailDescriptionData;
    }

}
