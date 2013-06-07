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

    private final String name;
    private boolean broken = false;
    private CashAccount cashAccount;
    private ShareDeposit shareDeposit;
    private List<LogEntry> logEntryList = new ArrayList<LogEntry>();

    public Player(String name) {
        this.name = name;
        broken = false;
        cashAccount = new CashAccount(0);
        shareDeposit = new ShareDeposit();

    }

    public void buy(Share share, int amount) throws NotEnoughMoneyException, NegativeValueException {
        cashAccount.subCash(share.getPrice() * amount);
        shareDeposit.addShare(share, amount);
        logEntryList.add(new LogEntry(LogEntry.BUY, share, amount));
    }

    public void sell(Share share, int amount) throws NotEnoughSharesException, NegativeValueException {
        shareDeposit.removeShare(share, amount);
        cashAccount.addCash(share.getPrice() * amount);
        logEntryList.add(new LogEntry(LogEntry.SELL, share, amount));
    }

    public void addCash(long cash) throws NegativeValueException {
        cashAccount.addCash(cash);
    }

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

    private boolean isBroken() {
        if (getCashAccountValue() <= 0) {
            broken = true;
        }
        return broken;
    }

    public String getStockList() {
        return shareDeposit.toString();
    }

    public String toString() {
        return rs.getString("PlayerName") + " " + name + " " + rs.getString("PlayerCash") + getCashAccountValue();
    }

    public void print(OutputStream stream, int sort, int output) throws IOException {
        Comparator comparator;
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(stream));
        //PrintWriter writer = new PrintWriter(stream);
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
           logger.fine("output: plain");
           for (LogEntry item : logEntryList) {
               w.write(item.toString());
               w.flush();
           }
        } else if (output == HTML) {
            logger.fine("output: html");
                w.write("<!DOCTYPE html PUBLIC-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd>\n");
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

    public void toFile(String filename, int sort, int output) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        print(out, sort, output);
        out.flush();
        out.close();
    }



    /*public void printHTML(OutputStream stream, String filename, int sort) {
        Comparator comparator;
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(stream));
        Writer fw = null;

        if (sort == SHARE) {
            comparator = new ShareComparator();
        } else if (sort == TIME) {
            comparator = new TimeComparator();
        } else {
            logger.warning(rs.getString("PlayerSort"));
            return;
        }

        Collections.sort(logEntryList, comparator);
        try {
            new FileWriter(filename);

            logger.fine("output: html");
            fw.write("<ul>");
            for (LogEntry item: logEntryList) {
                fw.write("<li>");
                fw.write(item.toString());
                fw.write("</li>");
            }
            fw.write("</ul>");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /*private String doPrint(Comparator c) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(logEntryList, c);
        for (LogEntry item : logEntryList) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    } */
}
