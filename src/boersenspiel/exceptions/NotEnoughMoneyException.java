package boersenspiel.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 19.04.13
 * Time: 16:02
 */
public class NotEnoughMoneyException extends RuntimeException{

    public NotEnoughMoneyException(String message) {   //TODO have to be handled
        super(message);
    }

    public NotEnoughMoneyException() {
    }
}
