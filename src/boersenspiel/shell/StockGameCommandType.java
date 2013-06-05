package boersenspiel.shell;

/**
 * User: jan
 * Date: 26.04.13
 * Time: 10:30
 */

import boersenspiel.interfaces.CommandTypeInfo;

public enum StockGameCommandType implements CommandTypeInfo {

    HELP ("help", " * list all StockGameCommandType"),
    EXIT ("exit", "exit program"),
    CREATEPLAYER ("crp",  "<playername> <cash> * create a new player by name", "boersenspiel.manager.AccountManagerImpl", "createPlayer", new Class[] {String.class, Long.class}),
    BUYSHARE ("bus",  "<playername> <sharename> <amount> * buy that amount of shares", "boersenspiel.manager.AccountManagerImpl", "buy", new Class[] {String.class, String.class, Integer.class}),
    SELLSHARE ("ses",  "<playername> <sharename> <amount> * sell that amount of shares", "boersenspiel.manager.AccountManagerImpl", "sell", new Class[] {String.class, String.class, Integer.class}),
    GETSTOCKS ("getst", "<playername> * list all player stocks", "boersenspiel.manager.AccountManagerImpl", "getStock", new Class[] {String.class}),
    GETALLSTOCKS ("getallst", " * list all available stocks", "boersenspiel.manager.ShareManagement", "listAll"),
    GETCASH ("gc", " <playername> * print players cashaccount Value", "boersenspiel.manager.AccountManagerImpl", "getCashAccountValue", new Class[] {String.class}),
    BOT ("bot", " <playername> * set player as bot",  "boersenspiel.manager.AccountManagerImpl", "botPlayer", new Class[] {String.class}),
    LANGUAGE ("lgg", "<language> * set language to english with 'en' or to german with 'de' ", "boersenspiel.manager.AccountManagerImpl", "setLocale", new Class[] {String.class}),
    PRINTLOG ("prl", "<playername> <sort> * print log as plain, sort parameter 1 = byShare, 2 = byTime", "boersenspiel.manager.AccountManagerImpl", "printPlain", new Class[] {String.class, Integer.class}),
    PRINTLOGHTML ("prlf", "<playername> <filename> <sort> * print log as html, sort parameter 1 = byShare, 2 = byTime", "boersenspiel.manager.AccountManagerImpl", "printHtml", new Class[] {String.class, String.class, Integer.class})
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
