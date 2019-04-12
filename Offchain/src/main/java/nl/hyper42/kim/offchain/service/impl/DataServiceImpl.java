package nl.hyper42.kim.offchain.service.impl;

import java.util.Optional;
import javax.annotation.ParametersAreNonnullByDefault;
import nl.hyper42.kim.offchain.dao.DataDao;
import nl.hyper42.kim.offchain.dao.SequenceDao;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;
import nl.hyper42.kim.offchain.models.DataDto;
import nl.hyper42.kim.offchain.models.DataHashDto;
import nl.hyper42.kim.offchain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Data Service implementation
 */
@Service
@ParametersAreNonnullByDefault
public class DataServiceImpl implements DataService {

    private static final String SEQUENCE_NAME = "data_sequence";
    /**
     * the DataDao for offchain storage
     */
    @Autowired
    private DataDao dataDao;

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public String submitData(TravelData data) {
        DataDto dataDto = new DataDto();
        dataDto.setId(Long.toString(sequenceDao.generateSequence(SEQUENCE_NAME)));
        dataDto.setClaimAddresses(data.getClaimAddresses());
        dataDto.setPhoto(data.getPhoto().getBytes());

        DataHashDto dataHashDto = new DataHashDto();
        dataHashDto.setAddress(data.getDataHashLocation());
        dataHashDto.setHash(data.getDataHash());
        dataHashDto.setSalt(data.getDataHashSalt());
        dataDto.setDataHash(dataHashDto);

        DataDto savedData = dataDao.save(dataDto);

        return savedData.getId();
    }

    @Override
    public Optional<TravelData> gatherDataById(String location) {
        Optional<TravelData> result = Optional.empty();

        Optional<DataDto> foundDataDto = dataDao.findById(location);
        if (foundDataDto.isPresent()) {
            DataDto dataDto = foundDataDto.get();
            TravelData data = new TravelData().withClaimAddresses(dataDto.getClaimAddresses()).withDataHash(dataDto.getDataHash().getHash())
                    .withDataHashLocation(dataDto.getDataHash().getAddress()).withDataHashSalt(dataDto.getDataHash().getSalt())
                    .withPhoto(new String(dataDto.getPhoto()));
            result = Optional.of(data);
        }

        return result;
    }
}
