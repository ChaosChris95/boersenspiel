package boersenspiel.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 19.04.13
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class NotEnoughMoneyException extends RuntimeException{

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
    }
}
