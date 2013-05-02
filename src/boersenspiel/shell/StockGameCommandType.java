package boersenspiel.shell;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:30
 */

import boersenspiel.interfaces.CommandTypeInfo;

public enum StockGameCommandType implements CommandTypeInfo {

    HELP ("help", " * list all StockGameCommandType"),
    EXIT ("exit", "exit program"),
    CREATEPLAYER ("crp",  "<playername> <cash> * create a new player by name", String.class, Long.class),
    GETASSETS("gp",  "<playername> * get assets of the player", String.class),
    BUYSHARE ("bus",  "<playername> <sharename> <amount> * buy that amount of shares", String.class, String.class, Integer.class),
    SELLSHARE ("ses",  "<playername> <sharename> <amount> * sell that amount of shares", String.class, String.class, Integer.class),
    GETSTOCKS ("getst", "<playername> * list all player stocks", String.class),
    GETALLSTOCKS ("getallst", " * list all available stocks"),
    GETCASH ("gc", " <playername> * print players cashaccount Value", String.class),
    BOT ("bot", " <playername> * set player as bot", String.class),
    CREATESHARE ("crs", "<sharename> <price> * create share with given price in system", String.class, Long.class),
    DELETESHARE ("des", "<sharename> * delete share from system ", String.class)    //TODO Implementierung in ShareManagement
    ;

    private String cmdName;
    private String helpText;
    private Class<?>[] paramType = new Class<?>[0];

    private StockGameCommandType(String cmdName, String helpText){

        this.cmdName = cmdName;
        this.helpText = helpText;

    }

    private StockGameCommandType(String cmdName, String helpText, Class<?> param){

        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType = new Class<?>[1];
        paramType[0] = param;

    }

    private StockGameCommandType(String cmdName, String helpText, Class<?> param,  Class<?> param2){

        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType = new Class<?>[2];
        paramType[0] = param;
        paramType[1] = param2;

    }

    private StockGameCommandType(String cmdName, String helpText, Class<?> param1, Class<?> param2, Class<?> param3){
        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType = new Class<?>[3];
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

    public Class<?>[] getParamTypes() {
        return paramType;
    }

    @Override
    public Object getTarget() {
        return null;
    }
}
