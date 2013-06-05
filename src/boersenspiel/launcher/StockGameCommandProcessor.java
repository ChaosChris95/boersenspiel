package boersenspiel.launcher;

import boersenspiel.account.PlayerAgent;
import boersenspiel.exceptions.CommandScannerException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.shell.*;
import sun.security.jca.GetInstance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 02.05.13
 * Time: 17:01
 */
public class StockGameCommandProcessor {

    private static Logger logger = Logger.getLogger("StockGameCommandProcessor");

    private BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    private PrintWriter shellWriter = new PrintWriter(System.out);
    private AccountManager accountManager;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public StockGameCommandProcessor(AccountManager accountManager) {

        this.accountManager = accountManager;

    }

    public void process() {

        CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);

        while (true) { // die Schleife über alle Kommandos, jeweils ein Durchlauf pro Eingabezeile
            //...
            CommandDescriptor command = new CommandDescriptor();
            //...

            try {
                commandScanner.fillInCommandDesc(command);
            } catch (CommandScannerException e) {
                logger.fine(rs.getString("GCPInput") + ": " + e.getMessage());
                continue;
            }

            StockGameCommandType commandType = (StockGameCommandType) command.getCommandType();
            if (commandType == StockGameCommandType.EXIT) {
                System.exit(0);
            } else {
                if (commandType == StockGameCommandType.HELP) {
                    StockGameCommandType en[] = StockGameCommandType.values();
                    for(int i = 0; i < en.length; i++) {
                        System.out.println(en[i].getCmdName() + ": " + en[i].getHelpText());
                    }
                } else {

                    try {
                    //load class
                    Class target = Class.forName(commandType.getTarget());

                    Method getInstance = null;
                    try {
                        //check getInstance();
                        getInstance = target.getMethod("getInstance", new Class[]{});
                    } catch (NoSuchMethodException e) {
                        throw new Error(rs.getString("GCPSingleton"));
                    }
                    //make class to object
                    Object targetInstance = getInstance.invoke(null, new Object[]{});
                    //search for correct method
                    Method method = target.getMethod(commandType.getFunc(), commandType.getParamTypes());
                    //run method
                    Object ret = method.invoke(targetInstance, command.getParams());  //command.getParams()
                           if (ret != null) {
                               logger.info(rs.getString("GCPAnswer") + ": \n " + ret);
                           }
                    } catch (NoSuchMethodException e) {
                        throw new Error(commandType.getFunc() + " " + rs.getString("GCPNo"));
                    } catch (IllegalAccessException e) {
                        throw new Error(e);
                    } catch (InvocationTargetException e) {
                        throw new Error(e);
                    } catch (ClassNotFoundException e) {
                        throw new Error(commandType.getTarget() + " " + rs.getString("GCPNo"));
                    }

                }
            }
        }
    }
}
