package wilsonoh.sagyo.exceptions;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid Command
 *
 */
public class InvalidCommandException extends SagyoException {

    private static final String exceptionMessage = "I'm sorry, but I don't know what that means :(";

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super(exceptionMessage);
    }
}
