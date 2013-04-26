package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 24.04.13
 * Time: 19:52
 */
public class ShareNameAlreadyExistsException extends RuntimeException {

    public ShareNameAlreadyExistsException(String msg){
        super(msg);
    }

    public ShareNameAlreadyExistsException(){

    }
}
