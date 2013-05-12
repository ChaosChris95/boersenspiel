package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 23.04.13
 * Time: 15:02
 */

public class NotEnoughSharesException extends Exception{

    public NotEnoughSharesException() {
    }

    public NotEnoughSharesException(String message) {
        super(message);
    }

}
