package nl.hyper42.kim.backend.dao;

/**
 * The Exception that is thrown if a runtime problem occurs during the interaction with Hyperledger Fabric.
 * 
 * @author Micha Wensveen
 */
public class HLFRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2687764222713082249L;

    /**
     * Instantiates a new HLFRuntimeException.
     */
    public HLFRuntimeException() {
        // no arg constructor
    }

    /**
     * Instantiates a new HLFRuntimeException.
     * 
     * @param message - String
     */
    public HLFRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new HLFRuntimeException.
     * 
     * @param message - String
     * @param cause - Throwable
     */
    public HLFRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
