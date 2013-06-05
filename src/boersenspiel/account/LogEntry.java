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

    public LogEntry(int action, Share share, int amount) {
        this.date = new Date();
        this.amount = amount;
        this.share = share.getName();
        this.action = action;

    }

    public boolean equals(Object o) {
        if (o instanceof LogEntry) {
            LogEntry i = (LogEntry) o;
            return this.action == i.action && this.date == i.date && this.amount == i.amount && this.share.equals(i.share);
        }
        return false;
    }

    public int compareTo(LogEntry o) {
        return this.share.compareTo(o.share);
    }

    public String toString() {
        String action = this.action == BUY ? rs.getString("LogBuy") : rs.getString("LogSell");
        return "\n" + this.date.toString() + "\n" + rs.getString("LogPlayer")+ " " + action + " " + this.amount + " "
                + rs.getString("LogShare")+ " " + this.share;
    }
}


