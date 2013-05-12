package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 19.04.13
 * Time: 16:02
 */
public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
    }
}
