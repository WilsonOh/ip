package wilsonoh.sagyo.exceptions;

/**
 * Base class for exceptions involving the operation of
 * the sagyo app
 *
 */
public class SagyoException extends Exception {
    SagyoException(String message) {
        super("Sagyo Error: " + message);
    }
}
