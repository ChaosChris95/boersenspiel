package boersenspiel.provider;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:00
 */

import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.stock.Share;

public abstract class StockPriceProvider {

    private ShareManagement shareManagement;
    private UserManagement userManagement;


    public StockPriceProvider(ShareManagement shareManagement, UserManagement userManagement){
        this.shareManagement = shareManagement;
        this.userManagement = userManagement;
    }

    public boolean isShareListed(String shareName){
          if (shareManagement.getShare(shareName) != null)
              return true;
          return false;
    }

    public long getCurrentShareRate(String shareName){
           return shareManagement.getSpecificRate(shareName);
    }

    public Share[] getAllSharesAsSnapShot(){
          return shareManagement.getShareList();    //TODO ist das damit gemeint?
    }

    protected abstract void updateShareRates();

    protected abstract void updateShareRate(Share share);

    public abstract void startUpdate() throws Exception;

    public Share getShare(String name){
          return shareManagement.getShare(name);
    }

    public long getShareItemValue(String playerName, String shareItemName) {
        return userManagement.getPlayer(playerName).getShareItemValue(shareItemName);

    }
}
