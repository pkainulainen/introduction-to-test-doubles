package net.petrikainulainen.testdoubles;

/**
 * This exception is thrown when no todo item is found
 * with the given id.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Creates a new exception object.
     * @param message   The error message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
