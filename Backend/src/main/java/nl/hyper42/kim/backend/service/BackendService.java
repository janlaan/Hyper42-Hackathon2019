package nl.hyper42.kim.backend.service;

import javax.annotation.ParametersAreNonnullByDefault;
import nl.hyper42.kim.backend.model.generated.api.TravelDataRequest;
import nl.hyper42.kim.backend.model.generated.api.TravelDataResponse;

/**
 * Data service to proces input and submit data to the blockchain
 *
 * @author Micha Wensveen
 */
@ParametersAreNonnullByDefault
public interface BackendService {

    /**
     * Submit data to the blockchain
     *
     * @param data data in string format
     * @return The id of the saved data
     */
    TravelDataResponse submitData(TravelDataRequest data);

}
