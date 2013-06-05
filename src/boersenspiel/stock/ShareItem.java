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


    public ShareItem(Share share, int shareAmount) {
        super(share.getName());
        this.shareAmount = shareAmount;
        value = share.getPrice() * shareAmount;
        this.share = share;
    }

    public int getShareAmount() {
        return shareAmount;
    }

    public void addShareAmount(int amount) throws NegativeValueException{
        if (amount < 0)
            throw new NegativeValueException("adding a negative amount of share is not allowed");
        value += this.share.getPrice() * amount;
        shareAmount += amount;
        logger.finest("Anzahl den Aktien hinzugefügt.");

    }

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

    public String toString() {
        return ("ShareItem " + name + " mit einem Gesamtwert von " + getValue());
    }

    public void merge(ShareItem i) throws NegativeValueException{
        this.addShareAmount(i.getShareAmount());
        this.addValue(i.getValue());
    }

    public boolean equals(Object o) {
        if (o instanceof ShareItem) {
           ShareItem i = (ShareItem) o;
            if (i.getName().equals(this.name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(ShareItem o) {
        return this.getName().compareTo(o.getName());
    }
}
