
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionsList implements Serializable
{

    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("activityDate")
    @Expose
    private String activityDate;
    @SerializedName("iconURL")
    @Expose
    private String iconURL;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("labelMiles")
    @Expose
    private String labelMiles;
    @SerializedName("labelXP")
    @Expose
    private String labelXP;
    @SerializedName("milesAmount")
    @Expose
    private Integer milesAmount;
    @SerializedName("xpAmount")
    @Expose
    private Integer xpAmount;
    @SerializedName("finalDestination")
    @Expose
    private String finalDestination;
    @SerializedName("xpActivity")
    @Expose
    private List<XpActivity> xpActivity = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    private final static long serialVersionUID = -5779075010188348212L;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public List<XpActivity> getXpActivity() {
        return xpActivity;
    }

    public void setXpActivity(List<XpActivity> xpActivity) {
        this.xpActivity = xpActivity;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

}
