package boersenspiel.manager;

import boersenspiel.account.LogEntry;
import boersenspiel.account.Player;
import boersenspiel.account.PlayerAgent;
import boersenspiel.exceptions.*;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.interfaces.AccountManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:34
 */

public class AccountManagerImpl implements AccountManager {

    private static Logger logger = Logger.getLogger("AccountManagerImpl");
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("boersenspiel");



    private static AccountManagerImpl instance = null;
    public static AccountManagerImpl getInstance() {
        if(AccountManagerImpl.instance == null) {
            AccountManagerImpl.instance = new AccountManagerImpl();
        }
        return AccountManagerImpl.instance;
    }

    private UserManagement userManagement;
    private ShareManagement shareManagement;
    private PlayerAgent playerAgent;

    public AccountManagerImpl() {
        this.shareManagement = ShareManagement.getInstance();
        this.userManagement = UserManagement.getInstance();
    }


    @Override
    public void createPlayer(String name, Long cash) throws NegativeValueException{
        try {
            userManagement.addPlayer(name, cash);
            logger.info(resourceBundle.getString("LogPlayer") + " " + name + " " + resourceBundle.getString("AMCreate") + " " + cash);
        } catch (PlayerAlreadyExistsException e) {;
            logger.warning(resourceBundle.getString("AMPlayerExist"));
        } catch (NegativeValueException e) {
            logger.warning(resourceBundle.getString("AMNegativ"));
        }
    }

    public void botPlayer(String name) {
        try {
            this.playerAgent = new PlayerAgent(userManagement.getPlayer(name));
            UpdateTimer.getInstance().addTask(this.playerAgent.getTask(), 10000, 10000);
            logger.info("Stelle" + name + " um auf Bot");
        } catch (PlayerDoesNotExistException e) {
            logger.warning(resourceBundle.getString("AMPlayerNo"));
        }

    }

    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        return userManagement.getPlayer(name);

    }

    @Override
    public void buy(String playerName, String shareName, Integer amount) throws NegativeValueException {
        try {
            userManagement.getPlayer(playerName).buy(shareManagement.getShare(shareName), amount);
            logger.info("Spieler " + playerName + " kaufte " + amount + " Aktien von " + shareName);
        } catch (NotEnoughMoneyException e) {
            logger.warning("Sie besitzen nicht genug Geld.");
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        } catch (NegativeValueException e) {
            logger.warning("Negativer Wert nicht erlaubt");
        }

    }

    @Override
    public void sell(String playerName, String shareName, Integer amount) {
        try {
            userManagement.getPlayer(playerName).sell(shareManagement.getShare(shareName), amount);
            logger.info("Spieler " + playerName + " verkaufte " + amount + " Aktien von " + shareName);
        } catch (NotEnoughSharesException e) {
            logger.warning("Sie besitzen nicht genug Anzahl dieser Aktien");
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        } catch (NegativeValueException e) {
            logger.warning("Negativer Wert nicht erlaubt");
        }

    }

    @Override
    public long getCashAccountValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getCashAccountValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return 0;
    }

    @Override
    public long getShareDepositValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getShareDepositValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return 0;
    }

    @Override
    public long getAssetValue(String name) {
        return (getCashAccountValue(name) + getShareDepositValue(name));
    }

    public String getStock(String name) {
        try {
            return userManagement.getPlayer(name).getStockList();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return null;
    }

    public void printHtml(String name, String filename, Integer sort) throws PlayerDoesNotExistException {
        try {
            userManagement.getPlayer(name).toFile(filename,sort,2);
        } catch (FileNotFoundException e) {
            logger.warning("Datei nicht gefunden.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPlain(String name, Integer sort) throws PlayerDoesNotExistException, IOException {
        userManagement.getPlayer(name).print(System.out, sort, 1);
    }

    public void setLocale(String locale) throws LanguageNotFoundException {
        if (locale.equals("en")) {
            Locale.setDefault(Locale.ENGLISH);
            logger.info("Set English");
        } else if (locale.equals("de")) {
            Locale.setDefault(Locale.GERMAN);
            logger.info("Umgestellt auf Deutsch");
        } else {
            throw new LanguageNotFoundException("Sprache nicht gefunden");
        }


    }



}
