package nl.hyper42.kim.backend.dao;

/**
 * The Exception that is thrown if a JsonProcessingException occurs when parsing a result from the Hyerledger Fabric to JSON
 * 
 * @author Kevin Kerkhoven
 */
public class HLFJsonParsingException extends HLFRuntimeException {

    private static final long serialVersionUID = 2687764222713082249L;

    /**
     * Instantiates a new HLFJsonParsingException.
     */
    public HLFJsonParsingException() {
        // no arg constructor
    }

    /**
     * Instantiates a new HLFJsonParsingException.
     * 
     * @param message - String
     */
    public HLFJsonParsingException(String message) {
        super(message);
    }

    /**
     * Instantiates a new HLFJsonParsingException.
     * 
     * @param message - String
     * @param cause - Throwable
     */
    public HLFJsonParsingException(String message, Throwable cause) {
        super(message, cause);
    }

}
