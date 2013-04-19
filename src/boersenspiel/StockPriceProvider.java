package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */

import java.util.Timer;
import java.util.TimerTask;

public abstract class StockPriceProvider {

    private ShareManagement shareManagement;

    public StockPriceProvider(ShareManagement shareManagement){
        this.shareManagement = shareManagement;
    }

    public boolean isShareListed(String shareName){
          if (shareManagement.getShare(shareName) != null)
              return true;
          return false;
    }

    public long getCurrentShareRate(String shareName){
           return 0;                        //TODO aktueller Kurswert bestimmen
    }

    public Share[] getAllSharesAsSnapShot(){
          return shareManagement.getShareList();    //TODO ist das damit gemeint?
    }

    protected abstract void updateShareRates();

    protected abstract void updateShareRate(Share share);

    public abstract void startUpdate();

    public Share getShare(String name){
          return shareManagement.getShare(name);
    }
}
