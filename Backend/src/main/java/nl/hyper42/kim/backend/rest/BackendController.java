package nl.hyper42.kim.backend.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.hyper42.kim.backend.model.generated.api.TravelDataRequest;
import nl.hyper42.kim.backend.model.generated.api.TravelDataResponse;
import nl.hyper42.kim.backend.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Rest Controller to interact with the Offchain microservice.
 *
 * @author Micha Wensveen
 */
@RestController
@RequestMapping("/register")
public final class BackendController {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    /** The data service. */
    @Autowired
    private BackendService dataService;

    /**
     * Add a new TravelData document.
     *
     * @param dataRequest The TravelData
     * @return the id of the stored data.
     */
    @PostMapping(value = "/", produces = { "application/json" })
    @ApiOperation(value = "Add a new TravelData for the InformationPiece")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "ID"), @ApiResponse(code = 400, message = "An error has occurred when attempting to parse request"),
                    @ApiResponse(code = 500, message = "A technical error has occurred when attempting to add data") })
    public @ResponseBody ResponseEntity<TravelDataResponse> submitData(
            @ApiParam(value = "The data request: TravelData", required = true) @RequestBody TravelDataRequest dataRequest) {

        LOG.trace("BackendController -> submitData: Starting query with data {}", dataRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(dataService.submitData(dataRequest));
    }
}