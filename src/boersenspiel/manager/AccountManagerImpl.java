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


    /**
     * Creates a player with a name and given cash
     * @param name String for player name
     * @param cash Long for cash value
     * @exception NegativeValueException if the given Long is a negative number
     */

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

    /**
     * will set certain player as bot
     * @param name String for player name
     */

    public void botPlayer(String name) {
        try {
            this.playerAgent = new PlayerAgent(userManagement.getPlayer(name));
            UpdateTimer.getInstance().addTask(this.playerAgent.getTask(), 10000, 10000);
            logger.info(rs.getString("AMBot")+ " " + name + " " + rs.getString("AMBot1"));
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }

    }

    /**
     * will check if player exists in the system or not
     * @param name String for player name
     * @return if player exists, will return player name as String, if not shows Exception
     * @e PlayerDoesNotExistException
     */

    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        return userManagement.getPlayer(name);

    }

    /**
     * buy a share for given Player with given amount
     * @param playerName String for player name
     * @param shareName String for share name
     * @param amount Integer for amount of share
     * @exception NegativeValueException if the given Integer is a negative number
     * @exception PlayerDoesNotExistException if the player is not in the system
     * @exception NotEnoughMoneyException if the player has not enough money
     */

    public void buy(String playerName, String shareName, Integer amount) throws NegativeValueException,PlayerDoesNotExistException,NotEnoughMoneyException {
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

    /**
     * sell a share for given Player with given amount
     * @param playerName String for player name
     * @param shareName String for share name
     * @param amount Integer for amount of share
     * @exception NegativeValueException if the given Integer is a negative number
     * @exception PlayerDoesNotExistException if the player is not in the system
     * @exception NotEnoughSharesException if the player has not enough shares to sell
     */

    public void sell(String playerName, String shareName, Integer amount) throws NegativeValueException,PlayerDoesNotExistException, NotEnoughSharesException {
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

    /**
     * get the amount of money a certain player has
     * @param playerName String for the player name
     * @return long for a certain players cash value, if Player does not exists, it returns 0
     */

    public long getCashAccountValue(String playerName) {
        try {
            long cash;
            cash = userManagement.getPlayer(playerName).getCashAccountValue();
            logger.info("Account: " + cash);
            return cash;
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }
        return 0;
    }

    /**
     * get the total Value of the ShareDeposit
     * @param playerName String for the player nem
     * @return long for a certain players Deposit Value, if Player dien not exists, it return 0
     */

    public long getShareDepositValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getShareDepositValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }
        return 0;
    }

    /**
     * get the value of an asset
     * @param name  String for the player name
     * @return long number consists of cash account and deposit value
     */

    public long getAssetValue(String name) {
        return (getCashAccountValue(name) + getShareDepositValue(name));
    }


    /**
     * prints the actual value of players cash account value und a list of shares he owns
     * @param name String for the player name
     * @return String name of deposit, player, value cash account and a list of the shares
     */

    public String getStock(String name) {
        try {
            String stocks;
            stocks = userManagement.getPlayer(name).getStockList();
            logger.info(stocks);
            return stocks;
        } catch (PlayerDoesNotExistException e) {
            logger.warning(rs.getString("AMPlayerNo"));
        }
        return null;
    }

    /**
     * will convert a given filename which contains a list of player activities
     * with specified parameters for sort type to a html file
     * @param name String for the player name
     * @param filename String for file name
     * @param sort Integer for sort type, 1 by share, 2 by time
     * @throws PlayerDoesNotExistException if the player down not exist
     */

    public void printHtml(String name, String filename, Integer sort) throws PlayerDoesNotExistException {
        try {
            userManagement.getPlayer(name).toFile(filename,sort,2);
        } catch (FileNotFoundException e) {
            logger.warning(rs.getString("AMNoFile"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints out the player activities with specified parameters for sort type
     * @param name String for the player name
     * @param sort Integer for sort type, 1 by share, 2 by time
     * @exception  PlayerDoesNotExistException if the player does not exist
     * @exception  IOException capture input/output errors
     */

    public void printPlain(String name, Integer sort) throws PlayerDoesNotExistException, IOException {
        userManagement.getPlayer(name).print(System.out, sort, 1);
    }

    /**
     * set the default Locale to given language
     * @param locale String for locale
     * @exception LanguageNotFoundException if wanted language is not supported
     */

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
