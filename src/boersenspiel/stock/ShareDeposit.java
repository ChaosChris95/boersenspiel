package boersenspiel.stock;

import boersenspiel.account.Asset;
import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.exceptions.NotEnoughSharesException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class ShareDeposit extends Asset {

    List<ShareItem> shareItemList = new ArrayList<ShareItem>();

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
    }

    public String getName() {
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
    }


    public long removeShare(Share share, int amount) throws NotEnoughSharesException {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(share.getName())) {
               if (item.getShareAmount() < amount) {
                   throw new NotEnoughSharesException("Sie besitzen nicht genügend Aktien");
               }
               item.removeShareAmount(amount);
               return item.getValue() * amount;
            }
        }
        throw new NotEnoughSharesException("Sie besitzen keine Aktie mit diesem Namen");
    }

    public void removeShareItem(ShareItem remove) throws NoSuchShareItemException {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(remove)) {
               shareItemList.remove(item);
               return;
            }
        }
        throw new NoSuchShareItemException("Nicht vorhanden");
    }

    public long getValue() {
        long totalValue = 0;
        for (ShareItem item : shareItemList) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    public long getShareItemValue(String shareItemName) {
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getValue();
            }
        }
        return 0;
    }

    public int getShareAmount(String shareItemName) {
        for (ShareItem item : shareItemList) {
            if (shareItemName.equals(item.getName())) {
                return item.getShareAmount();
            }
        }
        return 0;
    }

    public String print() {

        String output = "";
        for (ShareItem item : shareItemList) {
            output += (item.getName()) + "\n";
        }
        return output;
    }

    public String toString() {
        return ("ShareDeposit " + name + " mit einem Gesamtwert von " + getValue()
                + ", dass aus folgenden ShareItems besteht:\n"
                + print());
    }


}
