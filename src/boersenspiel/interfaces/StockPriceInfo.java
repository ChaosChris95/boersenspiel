package boersenspiel.interfaces;

import boersenspiel.stock.Share;

/**
 * User: jan
 * Date: 13.04.13
 * Time: 10:37
 */

public interface StockPriceInfo {

    /**
     * is this share an available share?
     * @param shareName
     * @return boolean
     */

    boolean isShareListed(String shareName);

    /**
     * get current price of a share
     * @param shareName
     * @return long
     */

    long getCurrentShareRate(String shareName);

    /**
     * returns all share as an Share[]
     * @return Share[]
     */

     Share[] getAllSharesAsSnapShot();

    /**
     * get the price of a ShareItem (= share package)
     * @param playerName
     * @param shareItemName
     * @return long
     */

    long getShareItemValue(String playerName, String shareItemName);

}
