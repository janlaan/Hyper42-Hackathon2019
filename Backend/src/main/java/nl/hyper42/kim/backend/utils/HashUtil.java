package nl.hyper42.kim.backend.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HashUtil class to determine hash value for the given payloads
 *
 * @author rshunmugam
 */
@Component
public final class HashUtil {
    /**
     * Logging instantiation for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(HashUtil.class);

    /**
     * Default Constructor
     */
    private HashUtil() {
        // private no-args constructor to prevent instantiation
    }

    /**
     * Getting hash which used SHA Algorithm
     * 
     * @param input
     * @return
     */
    public static String getSHA(String input) {
        LOG.trace("HashUtil -> getSHA called with input {}", input);
        String hashedInput = Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
        LOG.debug("HashUtil -> getSHA result {}", hashedInput);
        return hashedInput;
    }
}
