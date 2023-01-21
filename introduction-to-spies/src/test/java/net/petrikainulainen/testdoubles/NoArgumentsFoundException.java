package net.petrikainulainen.testdoubles;

/**
 * This exception is thrown when a spy doesn't find the arguments of
 * the requested method invocation.
 */
class NoArgumentsFoundException extends RuntimeException {

    /**
     * Creates a new exception. The message that's passed to the constructor
     * of the super class is created by using the {@link String#format(String, Object...)}
     * method.
     *
     * @param messageTemplate   A message template that uses the syntax of the
     *                          {@link String#format(String, Object...)} method.
     * @param arguments         The arguments which are used to transform the provided
     *                          message template into a concrete error message.
     */
    NoArgumentsFoundException(String messageTemplate, Object... arguments) {
        super(String.format(messageTemplate, arguments));
    }
}
