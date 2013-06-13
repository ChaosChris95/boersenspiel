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

    /**
     * Constructor to create ShareDeposit inherits from Asset
     * @see Asset
     */

    public ShareDeposit() {
        super("ShareDeposit");
    }

    /**
     * will add a ShareItem to existing ShareDeposit
     * @param add ShareItem which will be added
     * @exception NegativeValueException if merge will be negative
     */

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
        return name;
    }

    /**
     * will add a Share to existing ShareItemList
     * @param share Share which will be added
     * @param amount Integer how many Shares will be added
     * @exception NegativeValueException if the given amount is a negative number
     */

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

    /**
     * will remove a Share from existing ShareItemList
     * @param share Share which will be removed
     * @param amount Integer how many Shares will be removed
     * @exception NotEnoughSharesException if the given amount is higher as the shares which exists
     */

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

    /**
     * will remove a ShareItem from existing ShareDeposit
     * @param remove ShareItem which will be removed
     * @exception NoSuchShareItemException if the given ShareItem does not exist in ShareDeposit
     */

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

    /**
     * will create a long which represent the value of all single existing ShareItems
     * @return long totalValue
     */

    public long getValue() {
        long totalValue = 0;
        for (ShareItem item : shareItemList) {
            totalValue += item.getValue();
        }
        logger.finer("CashAccount.getValue()");
        return totalValue;
    }

    /**
     * will create a long which represent the value of a single ShareItem by name
     * @return long as value if ShareItem exists, else return 0
     */

    public long getShareItemValue(String shareItemName) {
        logger.finer("CashAccount.ShareItemValue(" + shareItemName + ")");
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getValue();
            }
        }
        return 0;
    }

    /**
     * will return the amount of a given ShareItem identified by name
     * @param shareItemName String for the ShareItem name
     * @return Integer if ShareItem exsists, else return 0
     */

    public int getShareAmount(String shareItemName) {
        logger.finer("CashAccount.getShareAmount(" + shareItemName + ")");
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getShareAmount();
            }
        }
        return 0;
    }

    /**
     * Name of item as String will be created for each item which exists in shareItemList
     * @return output for each item in shareItemList
     */

    private String print() {

        String output = "";
        for (ShareItem item : shareItemList) {
            output += (item.getName()) + "\n";
        }
        return output;
    }

    /**
     * String representation is "name=NAME, getValue=VALUE, print=PRINT"
     * Where NAME is the Name of the ShareDeposit, VALUE is the total Value of the ShareDeposit and PRINT is the method
     * to print out all names of each ShareItem in ShareDeposit
     * @return String
     */

    public String toString() {
        logger.finer("CashAccount.toString()");
        return ("ShareDeposit " + name + " mit einem Gesamtwert von " + getValue()
                + ", dass aus folgenden ShareItems besteht:\n"
                + print());
    }
}
