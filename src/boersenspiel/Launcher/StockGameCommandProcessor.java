package boersenspiel.Launcher;

import boersenspiel.interfaces.AccountManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import boersenspiel.shell.*;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 26.04.13
 * Time: 10:56
 */
public class StockGameCommandProcessor {

    private BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    private PrintWriter shellWriter = new PrintWriter(System.out);
    private AccountManager accountManager;

    public StockGameCommandProcessor(AccountManager accountManager){

        this.accountManager = accountManager;

    }

    /*public void process(){

        CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);

        while (true) { // die Schleife über alle Kommandos, jeweils ein Durchlauf pro Eingabezeile
            //...
            CommandDescriptor command = new CommandDescriptor();
            //...
            commandScanner.fillInCommandDesc(command);
            //...

            Object[] params = command.getParams();

            StockGameCommandType commandType = (StockGameCommandType)command.getCommandType();
            switch (commandType) {
                case EXIT: {}
                case HELP: {}
                case CREATEPLAYER: { }
            }
        }
        }
    }*/
}
