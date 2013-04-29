package boersenspiel.shell;

import boersenspiel.interfaces.CommandTypeInfo;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:33
 */

public class CommandDescriptor {

    private CommandTypeInfo<String> commandType;    //= new CommandTypeInfo<String>();
    //Objekt[]<T> params;
    //Generics?

    public CommandDescriptor(CommandTypeInfo commandType){
        this.commandType = commandType;
    }

}
