package boersenspiel.interfaces;

import boersenspiel.stock.Share;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 13.04.13
 * Time: 10:37
 */
public interface StockPriceInfo {

    /**
     * is this share an avaiable share?
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
}
