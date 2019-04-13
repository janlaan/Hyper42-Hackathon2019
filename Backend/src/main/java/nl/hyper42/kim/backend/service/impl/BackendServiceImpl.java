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
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import nl.hyper42.kim.backend.claim.ClaimCodes;
import nl.hyper42.kim.backend.dao.HLFInvoker;
import nl.hyper42.kim.backend.model.generated.api.Authorisation;
import nl.hyper42.kim.backend.model.generated.api.ClaimAddress;
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
            Optional<String[]> olderEightteen = registerOlderEightteen(passportData, authorisations);
            addClaimId(claimIds, olderEightteen);
            Optional<String[]> olderTwentyOne = registerOlderTwentyOne(passportData, authorisations);
            addClaimId(claimIds, olderTwentyOne);
            List<String> photoInfo = storeProfilePic(Base64.getDecoder().decode(data.getPhoto()));
            String salt = makeSalt();
            List<String> hashInfo = storeHash(claimIds, photoInfo.get(0), salt);
            return new TravelDataResponse()
                    .withClaimAddresses(claimIds.entrySet().stream()
                            .map(claim -> new ClaimAddress().withClaimName(claim.getKey()).withClaimAddress(claim.getValue())).collect(Collectors.toList()))
                    .withDataHash(hashInfo.get(1)).withDataHashAddress(hashInfo.get(0)).withDataHashSalt(salt).withPhotoAddress(photoInfo.get(0))
                    .withPhotoKey(photoInfo.get(1));
        } catch (IOException | InterruptedException e) {
            LOG.error("Cannot read passport", e);
            throw new ApplicationRuntimeException(e);
        }
    }

    private void addClaimId(Map<String, String> claimIds, Optional<String[]> newClaim) {
        if (newClaim.isPresent()) {
            claimIds.put(newClaim.get()[0], newClaim.get()[1]);
        }

    }

    private List<String> storeHash(Map<String, String> claimIds, String photoId, String salt) throws InterruptedException {
        String hash = HashUtil.getSHA(String.join("", claimIds.values()) + photoId + salt);
        String id = UUID.randomUUID().toString();
        hlfInvoker.invokeChaincode("registerHash", id, salt, hash);
        return Arrays.asList(id, hash);
    }

    private String makeSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return new String(salt);
    }

    private List<String> storeProfilePic(byte[] photo) throws InterruptedException {
        List<byte[]> photoParts = cryptoPhoto(photo);
        // part one is key, part two is cryptographicly photo.
        String id = UUID.randomUUID().toString();
        hlfInvoker.invokeChaincode("storeProfilePic", id, Base64.getEncoder().encodeToString(photoParts.get(1)));
        return Arrays.asList(id, Base64.getEncoder().encodeToString(photoParts.get(1)));

    }

    private List<byte[]> cryptoPhoto(byte[] photo) {
        int size = photo.length / 2;
        byte[] key = Arrays.copyOfRange(photo, 0, size);
        byte[] crypto = Arrays.copyOfRange(photo, size, photo.length);
        return Arrays.asList(key, crypto);
    }

    private Optional<String[]> registerOlderTwentyOne(PassportData passportData, List<Authorisation> authorisations) throws InterruptedException {
        return registerOlderThan(passportData, ClaimCodes.OlderTwentyOne.name(), 21, authorisations);
    }

    private Optional<String[]> registerOlderEightteen(PassportData passportData, List<Authorisation> authorisations) throws InterruptedException {
        return registerOlderThan(passportData, ClaimCodes.OlderEightteen.name(), 18, authorisations);
    }

    private Optional<Authorisation> findAuthorisation(String name, List<Authorisation> authorisations) {
        return authorisations.stream().filter(a -> a.getClaimName().equals(name)).findFirst();

    }

    private Optional<String[]> registerOlderThan(PassportData passportData, String claimName, int check, List<Authorisation> authorisations)
            throws InterruptedException {
        Optional<Authorisation> foundAuthorisation = findAuthorisation(claimName, authorisations);
        if (foundAuthorisation.isPresent()) {
            Authorisation authorisation = foundAuthorisation.get();
            String id = UUID.randomUUID().toString();
            LocalDate dob = LocalDate.parse(passportData.getDateOfBirth());
            boolean b = Period.between(dob, LocalDate.now()).getYears() >= check;
            registerClaim(id, claimName, Boolean.valueOf(b).toString(), authorisation.getWho(), authorisation.getWhere(), authorisation.getRole());
            return Optional.of(new String[] { claimName, id });
        }
        return Optional.empty();
    }

    private void registerClaim(String id, String claim, String answer, List<String> who, List<String> where, List<String> role) throws InterruptedException {
        hlfInvoker.invokeChaincode("registerClaim", id, claim, answer, toCommaSeperatedString(who), toCommaSeperatedString(where),
                toCommaSeperatedString(role));
    }

    private String toCommaSeperatedString(List<String> who) {
        return who.isEmpty() ? "" : String.join(",", who);
    }

}
