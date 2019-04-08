
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XpGlobal implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("amountLabel")
    @Expose
    private String amountLabel;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("endDateLabel")
    @Expose
    private String endDateLabel;
    private final static long serialVersionUID = 7161327658597591051L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAmountLabel() {
        return amountLabel;
    }

    public void setAmountLabel(String amountLabel) {
        this.amountLabel = amountLabel;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDateLabel() {
        return endDateLabel;
    }

    public void setEndDateLabel(String endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

}
