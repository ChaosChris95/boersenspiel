package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 01.05.13
 * Time: 10:34
 */
public class PlayerAlreadyExistsException extends RuntimeException{

    public PlayerAlreadyExistsException() {
    }

    public PlayerAlreadyExistsException(String msg) {
        System.out.println(msg);
    }

}
