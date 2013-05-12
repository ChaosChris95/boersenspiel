package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 12.05.13
 * Time: 21:17
 */
public class PlayerAlreadyExistsException extends Exception {
    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    public PlayerAlreadyExistsException() {
    }
}
