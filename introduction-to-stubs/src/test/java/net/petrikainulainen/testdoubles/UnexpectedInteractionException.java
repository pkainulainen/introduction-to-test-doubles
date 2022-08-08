package net.petrikainulainen.testdoubles;

/**
 * This exception is thrown when the system under test invokes our stub
 * and passes an unexpected argument to the invoked method.
 */
public class UnexpectedInteractionException extends RuntimeException{

    /**
     * Creates a new exception object.
     *
     * @param messageTemplate   A template which uses the format supported by
     *                          the {@link String#format(String, Object...)} method.
     * @param params            The parameters which are passed to the
     *                          {@link String#format(String, Object...)} method.
     */
    UnexpectedInteractionException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }
}
