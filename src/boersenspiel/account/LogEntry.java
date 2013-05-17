package boersenspiel.account;

import boersenspiel.AccountManagerProxy;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.stock.Share;

import java.io.IOException;
import java.util.Date;

/**
 * User: Peach
 * Date: 14.05.13
 * Time: 16:13
 */
public class LogEntry {

    public Date timeStamp;
    public int amount;
    public Share share;

    public LogEntry (Date timeStamp, String a, Share share, int amount) {
    }


}
