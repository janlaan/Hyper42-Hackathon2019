
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactions implements Serializable {

    private final static long serialVersionUID = 2267656925843938827L;
    @SerializedName("labelTransactions")
    @Expose
    private String labelTransactions;
    @SerializedName("labelDetails")
    @Expose
    private String labelDetails;
    @SerializedName("labelClaim")
    @Expose
    private String labelClaim;
    @SerializedName("moreTransactions")
    @Expose
    private MoreTransactions moreTransactions;
    @SerializedName("transactionsList")
    @Expose
    private List<TransactionsList> transactionsList = null;

    public String getLabelTransactions() {
        return labelTransactions;
    }

    public void setLabelTransactions(String labelTransactions) {
        this.labelTransactions = labelTransactions;
    }

    public String getLabelDetails() {
        return labelDetails;
    }

    public void setLabelDetails(String labelDetails) {
        this.labelDetails = labelDetails;
    }

    public String getLabelClaim() {
        return labelClaim;
    }

    public void setLabelClaim(String labelClaim) {
        this.labelClaim = labelClaim;
    }

    public MoreTransactions getMoreTransactions() {
        return moreTransactions;
    }

    public void setMoreTransactions(MoreTransactions moreTransactions) {
        this.moreTransactions = moreTransactions;
    }

    public List<TransactionsList> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<TransactionsList> transactionsList) {
        this.transactionsList = transactionsList;
    }

}
