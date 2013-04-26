package boersenspiel.shell;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:30
 */
public enum Commands {

    HELP ("help", " * list all Commands"),
    EXIT ("exit", "exit program"),
    CREATEPLAYER ("crp",  "<name> * create a new player by name", String.class),
    //BUYSHARE ("bus",  "<playername> <sharename> <amount> * buy that amount of shares", String.class, String.class, int.class),
    ;

    private String cmdName;
    private String helpText;
    private Class<?>[] paramType;

    private Commands(String cmdName, String helpText){

        this.cmdName = cmdName;
        this.helpText = helpText;

    }

    private Commands(String cmdName, String helpText, Class<?> param){

        this.cmdName = cmdName;
        this.helpText = helpText;
        paramType[0] = param;

    }
}
