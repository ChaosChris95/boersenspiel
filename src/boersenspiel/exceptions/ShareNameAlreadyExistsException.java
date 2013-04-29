package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 24.04.13
 * Time: 19:52
 */
public class ShareNameAlreadyExistsException extends Exception {

    public ShareNameAlreadyExistsException(String msg){
        super(msg);
    }

    public ShareNameAlreadyExistsException(){

    }
}
