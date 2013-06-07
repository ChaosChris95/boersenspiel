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
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");



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
            logger.info(rs.getString("LogPlayer") + " " + name + " " + rs.getString("AMCreate") + " " + cash);
        } catch (PlayerAlreadyExistsException e) {;
            logger.warning(rs.getString("AMPlayerExist"));
        } catch (NegativeValueException e) {
            logger.warning(rs.getString("AMNegativ"));
        }
    }

    public void botPlayer(String name) {
        try {
            this.playerAgent = new PlayerAgent(userManagement.getPlayer(name));
            UpdateTimer.getInstance().addTask(this.playerAgent.getTask(), 10000, 10000);
            logger.info(rs.getString("AMBot")+ " " + name + " " + rs.getString("AMBot1"));
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }

    }

    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        return userManagement.getPlayer(name);

    }

    @Override
    public void buy(String playerName, String shareName, Integer amount) throws NegativeValueException {
        try {
            userManagement.getPlayer(playerName).buy(shareManagement.getShare(shareName), amount);
            logger.info(rs.getString("LogPlayer")+" "+playerName + " " +rs.getString("AMBuy")+ " " + amount + rs.getString("LogShare")+ " " + shareName);
        } catch (NotEnoughMoneyException e) {
            logger.warning(rs.getString("CashNo"));
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        } catch (NegativeValueException e) {
            logger.warning(rs.getString("AMNegativ"));
        }

    }

    @Override
    public void sell(String playerName, String shareName, Integer amount) {
        try {
            userManagement.getPlayer(playerName).sell(shareManagement.getShare(shareName), amount);
            logger.info(rs.getString("LogPlayer")+" "+ playerName + " "+ rs.getString("AMSell")+" "+ amount + " " +rs.getString("LogShare")+ " " + shareName);
        } catch (NotEnoughSharesException e) {
            logger.warning(rs.getString("AMNoShare"));
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        } catch (NegativeValueException e) {
            logger.warning(rs.getString("AMNegativ"));
        }

    }

    @Override
    public long getCashAccountValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getCashAccountValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }
        return 0;
    }

    @Override
    public long getShareDepositValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getShareDepositValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
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
            logger.warning(rs.getString("AMPlayerNo"));
        }
        return null;
    }

    public void printHtml(String name, String filename, Integer sort) throws PlayerDoesNotExistException {
        try {
            userManagement.getPlayer(name).toFile(filename,sort,2);
        } catch (FileNotFoundException e) {
            logger.warning(rs.getString("AMNoFile"));
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
            logger.info("Set Language to English");
        } else if (locale.equals("de")) {
            Locale.setDefault(Locale.GERMAN);
            logger.info("Umgestellt auf Deutsch");
        } else {
            throw new LanguageNotFoundException(rs.getString("AMLang"));
        }


    }



}
