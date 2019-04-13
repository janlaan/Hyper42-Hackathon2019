package nl.hyper42.kim.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import nl.hyper42.kim.backend.claim.ClaimCodes;
import nl.hyper42.kim.backend.dao.HLFInvoker;
import nl.hyper42.kim.backend.model.generated.api.Authorisation;
import nl.hyper42.kim.backend.model.generated.api.TravelDataRequest;
import nl.hyper42.kim.backend.model.generated.api.TravelDataResponse;
import nl.hyper42.kim.backend.model.generated.model.PassportData;
import nl.hyper42.kim.backend.service.BackendService;
import nl.hyper42.kim.backend.utils.ApplicationRuntimeException;
import nl.hyper42.kim.backend.utils.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Backend Service implementation
 */
@Service
@ParametersAreNonnullByDefault
public class BackendServiceImpl implements BackendService {

    private static final Logger LOG = LoggerFactory.getLogger(BackendServiceImpl.class);

    /**
     * Instantiates a hlfInvoker
     */
    @Autowired
    private HLFInvoker hlfInvoker;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public TravelDataResponse submitData(TravelDataRequest data) {
        Map<String, String> claimIds = new HashMap<>();
        try {
            PassportData passportData = mapper.readValue(data.getPassportData(), PassportData.class);
            List<Authorisation> authorisations = data.getAuthorisation();
            registerOlderEightteen(passportData, claimIds, authorisations);
            registerOlderTwentyOne(passportData, claimIds, authorisations);
            String photoId = storeProfilePic(Base64.getDecoder().decode(data.getPhoto()));
            String salt = makeSalt();
            String hashId = storeHash(claimIds, photoId, salt);
        } catch (IOException | InterruptedException e) {
            LOG.error("Cannot read passport", e);
            throw new ApplicationRuntimeException(e);
        }
        return null;
    }

    private String storeHash(Map<String, String> claimIds, String photoId, String salt) throws InterruptedException {
        String id = UUID.randomUUID().toString();

        String hash = HashUtil.getSHA(String.join("", claimIds.values()) + photoId + salt);
        hlfInvoker.invokeChaincode("registerHash", id, salt, hash);
        return id;
    }

    private String makeSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return new String(salt);
    }

    private String storeProfilePic(byte[] photo) throws InterruptedException {
        List<byte[]> photoParts = cryptoPhoto(photo);
        // part one is key, part two is cryptographicly photo.
        String id = UUID.randomUUID().toString();
        hlfInvoker.invokeChaincode("storeProfilePic", id, Base64.getEncoder().encodeToString(photoParts.get(1)));
        return id;

    }

    private List<byte[]> cryptoPhoto(byte[] photo) {
        int size = photo.length / 2;
        byte[] key = Arrays.copyOfRange(photo, 0, size);
        byte[] crypto = Arrays.copyOfRange(photo, size, photo.length);
        return Arrays.asList(key, crypto);
    }

    private void registerOlderTwentyOne(PassportData passportData, Map<String, String> claimIds, List<Authorisation> authorisations)
            throws InterruptedException {
        registerOlderThan(passportData, claimIds, ClaimCodes.OlderTwentyOne.name(), 21, authorisations);
    }

    private void registerOlderEightteen(PassportData passportData, Map<String, String> claimIds, List<Authorisation> authorisations)
            throws InterruptedException {
        registerOlderThan(passportData, claimIds, ClaimCodes.OlderEightteen.name(), 18, authorisations);
    }

    private Optional<Authorisation> findAuthorisation(String name, List<Authorisation> authorisations) {
        return authorisations.stream().filter(a -> a.getClaimName().equals(name)).findFirst();

    }

    private void registerOlderThan(PassportData passportData, Map<String, String> claimIds, String claimName, int check, List<Authorisation> authorisations)
            throws InterruptedException {
        Optional<Authorisation> foundAuthorisation = findAuthorisation(claimName, authorisations);
        if (foundAuthorisation.isPresent()) {
            Authorisation authorisation = foundAuthorisation.get();
            String id = UUID.randomUUID().toString();
            LocalDate dob = LocalDate.parse(passportData.getDateOfBirth());
            boolean b = Period.between(dob, LocalDate.now()).getYears() >= check;
            registerClaim(id, claimName, Boolean.valueOf(b).toString(), authorisation.getWho(), authorisation.getWhere(), authorisation.getRole());
            claimIds.put(claimName, id);
        }
    }

    private void registerClaim(String id, String claim, String answer, List<String> who, List<String> where, List<String> role) throws InterruptedException {
        hlfInvoker.invokeChaincode("registerClaim", id, claim, answer, toCommaSeperatedString(who), toCommaSeperatedString(where),
                toCommaSeperatedString(role));
    }

    private String toCommaSeperatedString(List<String> who) {
        return who.isEmpty() ? "" : String.join(",", who);
    }

}
