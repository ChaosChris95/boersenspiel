package boersenspiel.shell;

import boersenspiel.interfaces.CommandTypeInfo;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:24
 */

public class CommandScanner {

    private CommandTypeInfo<String> commandTypeInfos;

    public CommandScanner(CommandTypeInfo<String> commandTypeInfos){
        this.commandTypeInfos = commandTypeInfos;
    }

    public void startScan(){

    }

}
