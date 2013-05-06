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
    CREATEPLAYER ("crp",  "<playername> <cash> * create a new player by name", "AccountManager", "createPlayer", new Class[] {String.class, Long.class}),
    BUYSHARE ("bus",  "<playername> <sharename> <amount> * buy that amount of shares", "AccountManager", "buy", new Class[] {String.class, String.class, Integer.class}),
    SELLSHARE ("ses",  "<playername> <sharename> <amount> * sell that amount of shares", "AccountManager", "sell", new Class[] {String.class, String.class, Integer.class}),
    GETSTOCKS ("getst", "<playername> * list all player stocks", "AccountManager", "getStock", new Class[] {String.class}),
    GETALLSTOCKS ("getallst", " * list all available stocks", "ShareManagement", "listAll"),
    GETCASH ("gc", " <playername> * print players cashaccount Value", "AccountManager", "getCashAccountValue", new Class[] {String.class}),
    BOT ("bot", " <playername> * set player as bot",  "AccountManager", "botPlayer", new Class[] {String.class}),
    CREATESHARE ("crs", "<sharename> <price> * create share with given price in system", "ShareManagement", "addShare", new Class[] {String.class, Long.class}),
    DELETESHARE ("des", "<sharename> * delete share from system ", "ShareManagement", "deleteShare", new Class[] {String.class})
    ;

    private String cmdName;
    private String helpText;
    private Class<?>[] paramType = new Class<?>[0];
    private String target;
    private String func;

    private StockGameCommandType(String cmdName, String helpText, String target, String func, Class<?> ... param){

        this.cmdName = cmdName;
        this.helpText = helpText;
        this.target = target;
        this.func = func;
        paramType = param;

    }

    private StockGameCommandType(String cmdName, String helpText) {
        this.cmdName = cmdName;
        this.helpText = helpText;
    }

    public String getCmdName(){
        return cmdName;
    }

    public String getHelpText(){
        return helpText;
    }

    public String getFunc() {
        return func;
    }

    public String getTarget() {
        return target;
    }

    public Class<?>[] getParamTypes() {
        return paramType;
    }

}
