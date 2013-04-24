package boersenspiel.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: avax
 * Date: 24.04.13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class SharenameAlreadyExistsException extends RuntimeException {

    public SharenameAlreadyExistsException(String msg){
        super(msg);
    }

    public SharenameAlreadyExistsException(){

    }
}
