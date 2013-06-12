package boersenspiel.account;

import boersenspiel.stock.Share;

import java.util.Date;
import java.util.ResourceBundle;

/**
 * User: Peach
 * Date: 14.05.13
 * Time: 16:13
 */
public class LogEntry implements Comparable<LogEntry> {


    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    public static final int BUY = 1;
    public static final int SELL = 2;

    public Date date = null;
    public int amount;
    public String share;
    public int action;

    /**
     * Constructor for LogEntry, set always new date for recoding player activities
     * @param action is a integer number 1 for buy and 2 for sell to differentiate the player activity
     * @param share  for the information what kind of share is used
     * @param amount record how many shares are bought
     */

    public LogEntry(int action, Share share, int amount) {
        this.date = new Date();
        this.amount = amount;
        this.share = share.getName();
        this.action = action;

    }

    /**
     * will check something?!
     * @param o Object to check
     * @return  true only when all LogEntries are true, if one is unequal it will return false
     */

    public boolean equals(Object o) {
        if (o instanceof LogEntry) {
            LogEntry i = (LogEntry) o;
            return this.action == i.action && this.date == i.date && this.amount == i.amount && this.share.equals(i.share);
        }
        return false;
    }

    /**
     * will sort LogEntries by Share
     * @param o LogEntry to compare with
     * @return  return sorted LogEntries by Share
     */

    public int compareTo(LogEntry o) {
        return this.share.compareTo(o.share);
    }

    /**
     * The string representation is "this.date=DATE, action=ACTION, amount=AMOUNT,
     * share=SHARE"
     * Where DATE is the recorded date,ACTION is buy or sell, AMOUNT is the amount of
     * share and SHARE is the share name.
     */

    public String toString() {
        String action = this.action == BUY ? rs.getString("LogBuy") : rs.getString("LogSell");
        return "\n" + this.date.toString() + "\n" + rs.getString("LogPlayer")+ " " + action + " " + this.amount + " "
                + rs.getString("LogShare")+ " " + this.share;
    }
}


