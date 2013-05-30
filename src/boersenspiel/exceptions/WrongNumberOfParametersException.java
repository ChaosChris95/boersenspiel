package boersenspiel.exceptions;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 18:40
 */

public class WrongNumberOfParametersException extends Exception{

    public WrongNumberOfParametersException(String msg){
        super(msg);
    }

    public WrongNumberOfParametersException(){

    }

}
