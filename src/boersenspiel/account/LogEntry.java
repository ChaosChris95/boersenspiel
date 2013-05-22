package boersenspiel.account;

import boersenspiel.AccountManagerProxy;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.stock.Share;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Peach
 * Date: 14.05.13
 * Time: 16:13
 */
public class LogEntry implements Comparable<LogEntry> {

    private Timestamp stamp = new Timestamp(System.currentTimeMillis());
    Date date = new Date(stamp.getTime());
    private int amount;
    private String share;
    private String action;

    public LogEntry(Date date, String action, Share share, int amount) {
        this.date = date;
        this.amount = amount;
        this.share = share.getName();
        this.action = action;

    }

    public Date getTimeStamp(){
        return this.date;
    }

    public String getShare() {
        return this.share;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getAction() {
        return this.action;
    }

    public boolean equals(Object o) {
        if (o instanceof LogEntry) {
            LogEntry i = (LogEntry) o;
            if (i.equals(LogEntry.this)) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(LogEntry o) {
        return this.getShare().compareTo(o.getShare());
    }

    public int compareToDate(LogEntry o) {
        return this.getTimeStamp().compareTo(o.getTimeStamp());
    }
}


