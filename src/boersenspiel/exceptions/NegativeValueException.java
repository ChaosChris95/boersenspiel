package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 22.04.13
 * Time: 15:21
 */
public class NegativeValueException extends RuntimeException{

    public NegativeValueException(String message) {
        super(message);
    }

    public NegativeValueException() {
    }
}
