package nl.hyper42.kim.offchain.service;

import java.util.Optional;
import javax.annotation.ParametersAreNonnullByDefault;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;

/**
 * Data service to submit and gather data documents from the offchain storage.
 *
 * @author Micha Wensveen
 */
@ParametersAreNonnullByDefault
public interface DataService {

    /**
     * Submit data to the offchain storage.
     *
     * @param data data in string format
     * @return The id of the saved data
     */
    String submitData(TravelData data);

    /**
     * Gather TravelData from the offchain storage by its ID.
     *
     * @param location The location (ID) of the data
     * @return TravelData if found otherwise empty.
     */
    Optional<TravelData> gatherDataById(String location);
}
