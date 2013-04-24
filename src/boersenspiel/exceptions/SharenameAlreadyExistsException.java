package boersenspiel.exceptions;

/**
 * User: Peach
 * Date: 24.04.13
 * Time: 19:52
 */
public class SharenameAlreadyExistsException extends RuntimeException {

    public SharenameAlreadyExistsException(String msg){
        super(msg);
    }

    public SharenameAlreadyExistsException(){

    }
}
