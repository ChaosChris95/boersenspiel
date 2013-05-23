package boersenspiel.account;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareDeposit;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    private String method;
    List<LogEntry> logEntryList = new ArrayList<LogEntry>();


    public Player(String name) {
        this.name = name;
        broken = false;
        cashAccount = new CashAccount(0);
        shareDeposit = new ShareDeposit();

    }

    public void buy(Share share, int amount) throws NotEnoughMoneyException {
        cashAccount.subCash(share.getPrice() * amount);
        shareDeposit.addShare(share, amount);
        String buy = method;

    }

    public void sell(Share share, int amount) throws NotEnoughSharesException, NegativeValueException{
        shareDeposit.removeShare(share, amount);
        cashAccount.addCash(share.getPrice() * amount);
        String sell = method;
    }

    public void addCash(long cash) throws NegativeValueException{
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

    public String getMethod() {
        return method;
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

    public void addLogEntry(LogEntry logEntry) {
        logEntryList.add(logEntry);
    }


    public String print() {

        String output = " ";
        Collections.sort(logEntryList);
        for (LogEntry item : logEntryList) {
            output += ("\n" + item.getTimeStamp() + "\n Aktie: " + item.getShare() + " " + item.getAction() + "\n Anzahl: " + item.getAmount() + "\n");
        }
        return output;
    }

}
