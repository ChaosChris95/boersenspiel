package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 12.05.13
 * Time: 21:05
 */
public class NoSuchShareItemException extends Exception {
    public NoSuchShareItemException(String message) {
        super(message);
    }

    public NoSuchShareItemException() {
    }
}
