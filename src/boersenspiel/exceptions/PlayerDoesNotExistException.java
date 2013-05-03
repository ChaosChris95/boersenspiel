package boersenspiel.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 01.05.13
 * Time: 10:47
 */

public class PlayerDoesNotExistException extends RuntimeException{

    public PlayerDoesNotExistException() {
    }

    public PlayerDoesNotExistException(String msg) {
        System.out.println(msg);
    }

}
