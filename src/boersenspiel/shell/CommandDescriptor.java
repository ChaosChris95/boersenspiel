package boersenspiel.shell;

import boersenspiel.interfaces.CommandTypeInfo;

/**
 * User: jan
 * Date: 26.04.13
 * Time: 10:33
 */

public class CommandDescriptor {

    public CommandTypeInfo commandType;
    public Object[] params;

    public CommandDescriptor(){
    }

    public Object[] getParams(){
        return this.params;
    }

    public CommandTypeInfo getCommandType(){
        return this.commandType;
    }






}
