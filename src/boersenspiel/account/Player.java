package boersenspiel.account;

import boersenspiel.account.comparators.ShareComparator;
import boersenspiel.account.comparators.TimeComparator;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareDeposit;


import java.util.*;

import static java.util.Collections.*;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class Player  {

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

    public void buy(Share share, int amount) throws NotEnoughMoneyException {
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

    public void subCash(long cash) throws Exception{
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
        return "Spieler mit dem Namen " + name + " und einem Kontostand von " + getCashAccountValue();
    }

    public String printByShare() {
        return this.doPrint(new ShareComparator());
    }
    public String printByDate() {
        return this.doPrint(new TimeComparator());
    }

    private String doPrint(Comparator c) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(logEntryList, c);
        for (LogEntry item : logEntryList) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
