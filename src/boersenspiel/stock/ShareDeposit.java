package boersenspiel.stock;

import boersenspiel.account.Asset;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.exceptions.NotEnoughSharesException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class ShareDeposit extends Asset {

    private List<ShareItem> shareItemList = new ArrayList<ShareItem>();
    private static Logger logger = Logger.getLogger("ShareDeposit");
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public ShareDeposit() {
        super("ShareDeposit");
    }

    public void addShareItem(ShareItem add) throws NegativeValueException{
        logger.finer("CashAccount.addShareItem(" + add + ")");
        for (ShareItem item : shareItemList) {
            if (item.equals(add)){
                logger.finest("Item exists merging");
                item.merge(add);
                return;
            }
        }
        logger.finest("new Item");
        shareItemList.add(add);

    }

    public String getName() {
        logger.finer("CashAccount.getName()");
        return name;
    }

    public void addShare(Share share, int amount) throws NegativeValueException {
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
        logger.finer("CashAccount.removeShare(" + share + amount + ")");
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(share.getName())) {
                if (item.getShareAmount() < amount) {
                    throw new NotEnoughSharesException(rs.getString("SDNoShare"));
                }
                item.removeShareAmount(amount);
                return;
            }
        }
        throw new NotEnoughSharesException(rs.getString("SDNotShare"));
    }

    public void removeShareItem(ShareItem remove) throws NoSuchShareItemException {//TODO Exception wrong placed method does not work
        logger.finer("CashAccount.removeShareItem(" + remove + ")");
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(remove.getName())) {
                shareItemList.remove(item);
                return;
            }
        }
        throw new NoSuchShareItemException(rs.getString("SDNoFound"));
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
