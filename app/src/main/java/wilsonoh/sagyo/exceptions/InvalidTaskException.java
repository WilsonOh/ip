package wilsonoh.sagyo.exceptions;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid Task
 *
 */
public class InvalidTaskException extends SagyoException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
