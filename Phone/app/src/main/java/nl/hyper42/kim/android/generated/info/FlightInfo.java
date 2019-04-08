
package nl.hyper42.kim.android.generated.info;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightInfo implements Serializable
{

    @SerializedName("individual")
    @Expose
    private Individual individual;
    @SerializedName("milesAndXPCounter")
    @Expose
    private MilesAndXPCounter milesAndXPCounter;
    @SerializedName("transactions")
    @Expose
    private Transactions transactions;
    private final static long serialVersionUID = 3385015030293939007L;

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public MilesAndXPCounter getMilesAndXPCounter() {
        return milesAndXPCounter;
    }

    public void setMilesAndXPCounter(MilesAndXPCounter milesAndXPCounter) {
        this.milesAndXPCounter = milesAndXPCounter;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

}
