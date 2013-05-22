package boersenspiel.account;

import boersenspiel.AccountManagerProxy;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.stock.Share;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Peach
 * Date: 14.05.13
 * Time: 16:13
 */
public class LogEntry {

    public Date timeStamp;
    public int amount;
    public Share share;
    List<LogEntry> logEntryList = new ArrayList<LogEntry>();

    public LogEntry() {

    }

    public LogEntry(Date timeStamp, String action, Share share, int amount) {
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.share = share;
    }

    public String getTimeStamp(){
        return this.timeStamp.toString();
    }

    public String getShare() {
        return this.share.toString();
    }

    public int getAmount() {
        return this.amount;
    }

    public void addLogEntry(LogEntry log) {
        logEntryList.add(log);

    }

    public String printLogEntry() {
        StringBuilder erg = new StringBuilder();
        erg.append("<br>");
        //Collections.sort(logEntryList);
        for (LogEntry logEntry : logEntryList) {
            erg.append(' ');
            erg.append(logEntry.getTimeStamp());
            erg.append(' ');
            erg.append(logEntry.getShare());
            erg.append(' ');
            erg.append(logEntry.getAmount());
            erg.append("<br>");

        }
        return erg.toString();
    }

    public boolean compareTo(LogEntry o) {
        return this.logEntryList.equals(o);
    }

}


