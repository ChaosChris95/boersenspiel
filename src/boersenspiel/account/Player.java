package boersenspiel.account;

import boersenspiel.account.comparators.ShareComparator;
import boersenspiel.account.comparators.TimeComparator;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareDeposit;


import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import static java.util.Collections.*;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class Player {

    private static Logger logger = Logger.getLogger("Player");
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    public static final int SHARE = 1;
    public static final int TIME = 2;

    public static final int PLAIN = 1;
    public static final int HTML = 2;

    //private Currency cur = Currency.getInstance(Locale.getDefault());
    private final String name;
    private boolean broken = false;
    private CashAccount cashAccount;
    private ShareDeposit shareDeposit;
    private List<LogEntry> logEntryList = new ArrayList<LogEntry>();

    /**
     * Constructor for Player set broken false, create new CashAccount and a ShareDeposit
     * @param name String for the player name
     */

    public Player(String name) {
        this.name = name;
        broken = false;
        cashAccount = new CashAccount(0);
        shareDeposit = new ShareDeposit();

    }

    /**
     * Method which player makes possible to buy shares
     * @param share  String for wanted share
     * @param amount Integer to know how many share player wants
     * @exception NotEnoughMoneyException if player does not have enough money to buy a share
     * @exception NegativeValueException if given amount is a negative number
     */

    public void buy(Share share, int amount) throws NotEnoughMoneyException, NegativeValueException {
        cashAccount.subCash(share.getPrice() * amount);
        shareDeposit.addShare(share, amount);
        logEntryList.add(new LogEntry(LogEntry.BUY, share, amount));
    }

    /**
     * Method which player makes possible to sell shares
     * @param share  String for wanted share
     * @param amount Integer to know how many share player wants
     * @exception NotEnoughSharesException if player does not have enough shares to sell
     * @exception NegativeValueException if given amount is a negative number
     */

    public void sell(Share share, int amount) throws NotEnoughSharesException, NegativeValueException {
        shareDeposit.removeShare(share, amount);
        cashAccount.addCash(share.getPrice() * amount);
        logEntryList.add(new LogEntry(LogEntry.SELL, share, amount));
    }

    /**
     * add Cash to the players cash account
     * @param cash  from type long
     */

    public void addCash(long cash) throws NegativeValueException {
        cashAccount.addCash(cash);
    }

    /**
     * subtract Cash from the players cash account
     * @param cash  from type long
     */

    public void subCash(long cash) throws Exception {
        cashAccount.subCash(cash);
    }


    public long getCashAccountValue() {
        return cashAccount.getValue();
    }

    public long getShareItemValue(String shareItemName) {
        return shareDeposit.getShareItemValue(shareItemName);
    }

    public long getShareDepositValue() {
        return shareDeposit.getValue();
    }

    public int getShareAmount(String shareItemName) {
        return shareDeposit.getShareAmount(shareItemName);
    }

    public String getName() {
        return name;
    }

    /**
     * Check if the Player is broken
     * @return if players Cash Account <= 0 its true, else is false
     */

    private boolean isBroken() {
        if (getCashAccountValue() <= 0) {
            broken = true;
        }
        return broken;
    }

    /**
     * @return StockList as String
     * @see ShareDeposit toString()
     */

    public String getStockList() {
        return shareDeposit.toString();
    }

    /**
     * The string representation is "name=NAME, getCashAccountValue()=VALUE"
     * Where NAME is the player name and VALUE is the Cash Account Value of player
     */

    public String toString() {
        return rs.getString("PlayerName") + " " + name + " " + rs.getString("PlayerCash") + getCashAccountValue();
    }

    /**
     * print all player activities by given properties
     * first creates a new BufferedReader give over a OutputStreamWriter.
     * Our stream give over to OutputStreamWriter.
     * Then the compare for sort type starts.
     * After that the player activities will be sorted.
     * The given output is picked by if-statement and saves the activities in BufferedWriter.
     * @param stream OutputStream
     * @param sort   Integer for sort type, 1 will sort by Share, 2 will sort by Time
     * @param output Integer for output type, 1 as plain, 2 as html document
     */

    public void print(OutputStream stream, int sort, int output) throws IOException {
        Comparator comparator;
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(stream));
        if (sort == SHARE) {
            comparator = new ShareComparator();
        } else if (sort == TIME) {
            comparator = new TimeComparator();
        } else {
            logger.warning(rs.getString("PlayerSort"));
            return;
        }

        Collections.sort(logEntryList, comparator);
        if (output == PLAIN) {
           for (LogEntry item : logEntryList) {
               w.write(item.toString());
               w.flush();
           }
        } else if (output == HTML) {
                w.write("<html> \n");
                w.write("<ul> \n");
            for (LogEntry item: logEntryList) {
                w.write("<li> \n");
                w.write(item.toString());
                w.write("</li> \n");
            }
            w.write("</ul> \n");
            w.write("</html> \n");
            w.flush();
        } else {
            logger.warning(rs.getString("PlayerTyp"));
        }
    }

    /**
     * convert the content in BufferedWriter into a html file
     * @param filename  String to give the file a name
     * @param sort      Integer for sort type, 1 sort by Share, 2 sort by Time
     * @param output    Integer for output type is always given as 2
     */

    public void toFile(String filename, int sort, int output) throws IOException {
        FileOutputStream out = new FileOutputStream(filename + ".html");
        print(out, sort, output);
        out.flush();
        out.close();
    }

}
