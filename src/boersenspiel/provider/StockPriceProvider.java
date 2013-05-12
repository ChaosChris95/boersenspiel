package boersenspiel.provider;

/**
 * User: jan
 * Date: 15.04.13
 * Time: 14:00
 */

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerDoesNotExistException;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.stock.Share;

public abstract class StockPriceProvider {

    protected ShareManagement shareManagement;
    private UserManagement userManagement;


    public StockPriceProvider(){
        this.shareManagement = ShareManagement.getInstance();
        this.userManagement = UserManagement.getInstance();
    }

    public boolean isShareListed(String shareName){
          if (shareManagement.getShare(shareName) != null)
              return true;
          return false;
    }

    public long getCurrentShareRate(String shareName){
           return shareManagement.getSpecificRate(shareName);
    }

    /*public Share[] getAllSharesAsSnapShot(){
          return shareManagement.cloneShareList();
    }*/

    protected abstract void updateShareRates() throws NegativeValueException;

    protected abstract void updateShareRate(Share share);

    public abstract void startUpdate() throws Exception;

    public Share getShare(String name){
          return shareManagement.getShare(name);
    }

    public long getShareItemValue(String playerName, String shareItemName) throws PlayerDoesNotExistException {
        return userManagement.getPlayer(playerName).getShareItemValue(shareItemName);

    }
}
