package boersenspiel.stock;

import boersenspiel.account.Asset;
import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.exceptions.NotEnoughSharesException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class ShareDeposit extends Asset {

    private List<ShareItem> shareItemList = new ArrayList<ShareItem>();
    private static Logger logger = Logger.getLogger("ShareDeposit");

    public ShareDeposit() {
        super("ShareDeposit");
    }

    public void addShareItem(ShareItem add) {

        for (ShareItem item : shareItemList) {
            if (item.equals(add)){
                item.merge(add);
                return;
            }
        }
        shareItemList.add(add);
        logger.finer("CashAccount.addShareItem(" + add + ")");
    }

    public String getName() {
        logger.finer("CashAccount.getName()");
        return name;
    }

    public void addShare(Share share, int amount) {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(share.getName())) {
                item.addShareAmount(amount);
                return;
            }
        }
        addShareItem(new ShareItem(share, amount));
        logger.finer("CashAccount.addShare(" + share + amount + ")");
    }


    public void removeShare(Share share, int amount) throws NotEnoughSharesException {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(share.getName())) {
                if (item.getShareAmount() < amount) {
                    throw new NotEnoughSharesException("Sie besitzen nicht genügend Aktien");
                }
                item.removeShareAmount(amount);
            }
        }
        logger.finer("CashAccount.removeShare(" + share + amount + ")");
        throw new NotEnoughSharesException("Sie besitzen keine Aktie mit diesem Namen");
    }

    public void removeShareItem(ShareItem remove) throws NoSuchShareItemException {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(remove)) {
                shareItemList.remove(item);
                return;
            }
        }
        logger.finer("CashAccount.removeShareItem(" + remove + ")");
        throw new NoSuchShareItemException("Nicht vorhanden");
    }

    public long getValue() {
        long totalValue = 0;
        for (ShareItem item : shareItemList) {
            totalValue += item.getValue();
        }
        logger.finer("CashAccount.getValue()");
        return totalValue;
    }

    public long getShareItemValue(String shareItemName) {
        logger.finer("CashAccount.ShareItemValue(" + shareItemName + ")");
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getValue();
            }
        }
        return 0;
    }

    public int getShareAmount(String shareItemName) {
        logger.finer("CashAccount.getShareAmount(" + shareItemName + ")");
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getShareAmount();
            }
        }
        return 0;
    }

    private String print() {

        String output = "";
        for (ShareItem item : shareItemList) {
            output += (item.getName()) + "\n";
        }
        return output;
    }

    public String toString() {
        logger.finer("CashAccount.toString()");
        return ("ShareDeposit " + name + " mit einem Gesamtwert von " + getValue()
                + ", dass aus folgenden ShareItems besteht:\n"
                + print());
    }
}
