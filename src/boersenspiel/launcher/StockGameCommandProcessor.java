package boersenspiel.launcher;

import boersenspiel.account.Player;
import boersenspiel.exceptions.CommandScannerException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.interfaces.CommandTypeInfo;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.shell.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    public void process(){

        CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);

        while (true) { // die Schleife über alle Kommandos, jeweils ein Durchlauf pro Eingabezeile
            //...
            CommandDescriptor command = new CommandDescriptor();
            //...

            try {
                commandScanner.fillInCommandDesc(command);
            } catch (CommandScannerException e) {
                System.out.println("Fehlerhafte Eingabe: " + e.getMessage());
                continue;
            }
            //...

            Object[] params = command.getParams();

            StockGameCommandType commandType = (StockGameCommandType)command.getCommandType();
            switch (commandType) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    for(int i = 0; i < StockGameCommandType.values().length; i++) {
                        System.out.println(StockGameCommandType.values()[i].getHelpText());
                    }
                    break;
                case CREATEPLAYER:
                    UserManagement.getInstance().addPlayer((String) params[0], (Long) params[1]);
                    System.out.println("Spieler " + (String) params[0] + " erstellt mit einem Accountwert von " + (Long) params[1]);
                    break;
                case BUYSHARE:
                    UserManagement.getInstance().getPlayer((String) params[0]).buy(
                            ShareManagement.getInstance().getShare((String) params[1]),
                            (Integer) params[2]);
                    System.out.println("Spieler " + (String) params[0] + " kaufte " + (Integer) params[2] + " Aktien von " + (String) params[1]);
                    break;
                case SELLSHARE:
                    UserManagement.getInstance().getPlayer((String) params[0]).sell(
                            ShareManagement.getInstance().getShare((String) params[1]),
                            (Integer) params[2]);

                    System.out.println("Spieler " + (String) params[0] + " verkaufte " + (Integer) params[2] + " Aktien von " + (String) params[1]);
                    break;
                case GETALLSTOCKS:
                    System.out.println(ShareManagement.getInstance().listAll());
                    break;
                case GETSTOCKS:
                    System.out.println(UserManagement.getInstance().getPlayer((String) params[0]).getStockList());
                    break;
                case GETCASH:
                    System.out.println(UserManagement.getInstance().getPlayer((String) params[0]).getCashAccountValue());
                    break;
                case CREATESHARE:
                    try {
                        ShareManagement.getInstance().addShare((String) params[0], (Long) params[1]);
                    } catch (ShareNameAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Aktie " + (String) params[0] + " mit einem Preis von " + (Long) params[1] + " erstellt.");
                    break;
            }
        }
    }
}
