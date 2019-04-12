package nl.hyper42.kim.offchain.dao;

import nl.hyper42.kim.offchain.models.DataDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface DataDao used to generate the Repository that can be used to interact with the MongoDB.
 *
 * @author Micha Wensveen
 */
@Repository
public interface DataDao extends MongoRepository<DataDto, String> {

}
