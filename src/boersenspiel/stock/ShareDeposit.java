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

    //ShareItem[] shareItemList = new ShareItem[0];
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


        /*for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName() == add.getName()) {
                shareItemList[i].addValue(add.getValue());
                shareItemList[i].addShareAmount(add.getShareAmount());
            }
        }

        ShareItem[] temporal = new ShareItem[shareItemList.length + 1];
        for (int i = 0; i < shareItemList.length; i++) {
            temporal[i] = shareItemList[i];
        }
        temporal[temporal.length - 1] = add;
        shareItemList = temporal;  */
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


        /*for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName().equals(share.getName())) {
                shareItemList[i].addShareAmount(amount);
                return;
            }
        }
        addShareItem(new ShareItem(share, amount));*/


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

        /*boolean exists = false;
        for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName().equals(share.getName())) {
                exists = true;
                if (getShareAmount(shareItemList[i].getName()) < amount)
                    throw new NotEnoughSharesException("Sie besitzen nicht genügend Aktien mit diesen Namen!");
                shareItemList[i].removeShareAmount(amount);
                return shareItemList[i].getValue() * amount;
            }
            if (!exists) {
                throw new NotEnoughSharesException("Sie besitzen keine Aktie diesen Namens!");
            }
        }
        return 0;*/
    }

    public void removeShareItem(ShareItem remove) throws NoSuchShareItemException {
        for (ShareItem item : shareItemList) {
            if (item.getName().equals(remove)) {
               shareItemList.remove(item);
               return;
            }
        }
        throw new NoSuchShareItemException("Nicht vorhanden");


        /*ShareItem[] temporal = new ShareItem[shareItemList.length - 1];
        boolean identified = false;
        for (int i = 0; i < shareItemList.length - 1; i++) {
            if (shareItemList[i] == remove)
                identified = true;
            temporal[i] = shareItemList[i + 1];
            if (identified) {
                temporal[i] = shareItemList[i + 1];
            } else
                temporal[i] = shareItemList[i];
        }
        shareItemList = temporal;   */
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
