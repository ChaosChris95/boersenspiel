package boersenspiel.stock;

import boersenspiel.account.Asset;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughSharesException;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34t
 */

public class ShareItem extends Asset implements Comparable<ShareItem> {

    private static Logger logger = Logger.getLogger("ShareItem");
    private int shareAmount;
    private long value;
    private final Share share;


    /**
     * Constructor to create a ShareItem inherits from Asset
     * @param share Object Share
     * @param shareAmount Integer number which represent how many shares
     * @see Asset
     */


    public ShareItem(Share share, int shareAmount) {
        super(share.getName());
        this.shareAmount = shareAmount;
        value = share.getPrice() * shareAmount;
        this.share = share;
    }

    /**
     * get the amount of shareItem
     * @return Integer shareAmount
     */

    public int getShareAmount() {
        return shareAmount;
    }

    /**
     * add a given amount to an existing shareItem
     * @param amount Integer which will be added to shareAmount
     * @exception NegativeValueException if the given amount is a negative number
     */

    public void addShareAmount(int amount) throws NegativeValueException{
        if (amount < 0)
            throw new NegativeValueException("adding a negative amount of share is not allowed");
        value += this.share.getPrice() * amount;
        shareAmount += amount;
        logger.finest("Anzahl den Aktien hinzugefügt.");

    }

    /**
     * subtract a given amount to an existing shareItem
     * @param amount Integer which will be subtracted to shareAmount
     * @exception NotEnoughSharesException if the number which will be subtract is higher as the number of share which exist
     */

    public void removeShareAmount(int amount) throws NotEnoughSharesException {
            if (amount > shareAmount) {
            } else {
                shareAmount -= amount;
                value -= this.share.getPrice()*amount;
                logger.finest("Anzahl den Aktien abgezogen.");
            }

    }

    public long getValue() {
        return value;
    }

    public void setValue(long set) {
        value = set;
    }

    public void addValue(long add) {
        value += add;
    }

    /**
     * String representation is "name=NAME, getValue=VALUE"
     * Where NAME is the Name of the ShareDeposit, VALUE is the total Value of the ShareDeposit
     * @return String
     */

    public String toString() {
        return ("ShareItem " + name + " mit einem Gesamtwert von " + getValue());
    }

    /**
     * merge the amount and value from a given ShareItem
     * @param i ShareItem which will be merged
     * @throws NegativeValueException if amount or value is a negative number
     */

    public void merge(ShareItem i) throws NegativeValueException{
        this.addShareAmount(i.getShareAmount());
        this.addValue(i.getValue());
    }

    /**
     * checks if the given object is a instance of ShareItem and will collate with existing ShareItems by name
     * @param o Object
     * @return true if its equal, else return false
     */

    public boolean equals(Object o) {
        if (o instanceof ShareItem) {
           ShareItem i = (ShareItem) o;
            if (i.getName().equals(this.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compare to different ShareItem by name
     * @param o ShareItem which will be compared with
     * @return Integer
     */

    @Override
    public int compareTo(ShareItem o) {
        return this.getName().compareTo(o.getName());
    }
}
