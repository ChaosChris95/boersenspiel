package boersenspiel.exceptions;

/**
 * User: jan
 * Date: 01.05.13
 * Time: 10:54
 */

public class ShareDoesNotExistException extends RuntimeException{

    public ShareDoesNotExistException() {
    }

    public ShareDoesNotExistException(String msg) {
        System.out.println(msg);
    }

}
