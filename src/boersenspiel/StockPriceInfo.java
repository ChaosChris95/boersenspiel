package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 13.04.13
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
public interface StockPriceInfo {

    boolean isShareListed(String shareName);

    long getCurrentShareRate(String shareName);

    Share[] getAllSharesAsSnapShot();
}
