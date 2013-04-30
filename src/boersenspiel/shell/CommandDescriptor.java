package boersenspiel.shell;

import boersenspiel.interfaces.CommandTypeInfo;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:33
 */

public class CommandDescriptor {

    private CommandTypeInfo<String> commandType;;
    //Objekt[]<T> params;
    //Generics?

    public CommandDescriptor(CommandTypeInfo commandType){
        this.commandType = commandType;
    }


    //Woher wissen wir welche Typen gecastet werden?
    //Welche Strings sind welche Casts
    //Wo wirds übergeben und umgewandelt

}
