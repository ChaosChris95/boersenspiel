package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 01.05.13
 * Time: 10:47
 */

public class PlayerDoesNotExistException extends Exception{

    public PlayerDoesNotExistException() {
    }

    public PlayerDoesNotExistException(String msg) {
        System.out.println(msg);
    }

}
