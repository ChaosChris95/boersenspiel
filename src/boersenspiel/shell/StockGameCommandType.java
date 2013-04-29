package boersenspiel.shell;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:30
 */

import boersenspiel.interfaces.CommandTypeInfo;

public enum StockGameCommandType implements CommandTypeInfo{

    HELP ("help", " * list all StockGameCommandType"),
    EXIT ("exit", "exit program"),
    CREATEPLAYER ("crp",  "<playername> * create a new player by name", String.class),
    GETASSETS("gp",  "<playername> * get assets of the player", String.class),
    BUYSHARE ("bus",  "<playername> <sharename> <amount> * buy that amount of shares", String.class, String.class, int.class),
    SELLSHARE ("bus",  "<playername> <sharename> <amount> * sell that amount of shares", String.class, String.class, int.class),
    ;

    private String cmdName;
    private String helpText;
    private Class<?>[] paramType;
    //private commandTypeInfo commandType;    //not sure

    private StockGameCommandType(String cmdName, String helpText){

        this.cmdName = cmdName;
        this.helpText = helpText;

    }

    private StockGameCommandType(String cmdName, String helpText, Class<?> param){

        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType[0] = param;

    }

    private StockGameCommandType(String cmdName, String helpText, Class<?> param1, Class<?> param2, Class<?> param3){
        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType[0] = param1;
        paramType[1] = param2;
        paramType[2] = param3;
    }

    public String getCmdName(){
        return cmdName;
    }

    public String getHelpText(){
        return helpText;
    }

    public Class<?>[] getParamTypes(){
        return paramType;
    }
}
