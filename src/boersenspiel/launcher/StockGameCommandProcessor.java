package boersenspiel.launcher;

import boersenspiel.account.PlayerAgent;
import boersenspiel.exceptions.CommandScannerException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.shell.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 02.05.13
 * Time: 17:01
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

            StockGameCommandType commandType = (StockGameCommandType) command.getCommandType();
            if(commandType == StockGameCommandType.EXIT) {
                // Exit
            } else if(commandType == StockGameCommandType.HELP) {
                // Help
            } else {
                String target = commandType.getTarget();
                String func = commandType.getFunc();

                //TODO
            }



            //Object[] params = command.getParams();
            //StockGameCommandType commandType = (StockGameCommandType)command.getCommandType();

            /*switch (commandType) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    StockGameCommandType en[] = StockGameCommandType.values();
                    for(int i = 0; i < en.length; i++) {
                        System.out.println(en[i].getCmdName() + ": " + en[i].getHelpText());
                    }
                    break;
                case CREATEPLAYER:
                    accountManager.createPlayer((String) params[0], (Long) params[1]);
                    System.out.println("Spieler " + (String) params[0] + " erstellt mit einem Accountwert von " + (Long) params[1]);
                    break;
                case BUYSHARE:
                    try {
                        accountManager.buy((String) params[0], (String) params[1], (Integer) params[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Spieler " + (String) params[0] + " kaufte " + (Integer) params[2] + " Aktien von " + (String) params[1]);
                    break;
                case SELLSHARE:
                    try {
                        accountManager.sell((String) params[0], (String) params[1], (Integer) params[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Spieler " + (String) params[0] + " verkaufte " + (Integer) params[2] + " Aktien von " + (String) params[1]);
                    break;
                case GETALLSTOCKS:
                    System.out.println(ShareManagement.getInstance().listAll());
                    break;
                case GETSTOCKS:
                    System.out.println(UserManagement.getInstance().getPlayer((String) params[0]).getStockList());
                    break;
                case GETCASH:
                    System.out.println(accountManager.getCashAccountValue((String) params[0]));
                    break;
                case CREATESHARE:
                    try {
                        ShareManagement.getInstance().addShare((String) params[0], (Long) params[1]);
                    } catch (ShareNameAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Aktie " + (String) params[0] + " mit einem Preis von " + (Long) params[1] + " erstellt.");
                    break;
                case DELETESHARE:
                    ShareManagement.getInstance().deleteShare((String) params[0]);
                    System.out.println("Aktien " + (String) params[0] + " wurde aus dem System gelöscht");
                    break;
                case BOT:
                    PlayerAgent p = new PlayerAgent(UserManagement.getInstance().getPlayer((String) params[0]));
                    //p.update(wtd);
                    UpdateTimer.getInstance().addTask(p.getTask(), 1000, 1000);
                    System.out.println("Stelle" + (String) params[0] + " um auf Bot");
                    break;
                 */


            }
        }
    }
}
