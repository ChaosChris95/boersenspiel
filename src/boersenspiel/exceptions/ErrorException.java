package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 24.04.13
 * Time: 15:34
 */

public class ErrorException extends Error {

    public ErrorException(String message) {
        super(message);
    }

    public ErrorException() {
    }
}
