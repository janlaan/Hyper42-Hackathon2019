package nl.hyper42.kim.backend.utils;

/**
 * The Exception that is thrown by this Application if a runtime problem occures.
 * 
 * @author Micha Wensveen
 */
public class ApplicationRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2687764222713082249L;

    /**
     * Instantiates a new ApplicationRuntimeException.
     */
    public ApplicationRuntimeException() {
        // Default Constructor
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     */
    public ApplicationRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param cause - Throwable
     */
    public ApplicationRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     * @param cause - Throwable
     */
    public ApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
