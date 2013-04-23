package boersenspiel.account;

import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareDeposit;

/**
 * Created with IntelliJ IDEA.
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class Player {

    private final String name;
    private boolean broken = false;
    private CashAccount cashAccount;
    private ShareDeposit shareDeposit;

    public Player(String name) {
        this.name = name;
        broken = false;
        cashAccount = new CashAccount(0);
        shareDeposit = new ShareDeposit();

    }

    public void buy(Share share, int amount) throws NotEnoughMoneyException {
        try {
            cashAccount.subCash(share.getPrice() * amount);
        } catch (NotEnoughMoneyException n) {}
        shareDeposit.addShare(share, amount);

    }

    public void sell(Share share, int amount) throws NotEnoughSharesException{  //TODO Exception not enough amount or no share
        try{
            shareDeposit.removeShare(share, amount);
        } catch (NotEnoughSharesException s) {}
        cashAccount.addCash(share.getPrice() * amount);
    }

    public void addCash(long cash) {
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

    public boolean isBroken() {     //TODO necessary?
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

}
