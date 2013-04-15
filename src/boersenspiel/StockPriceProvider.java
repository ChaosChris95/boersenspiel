package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class StockPriceProvider {

    public boolean isShareListed(String shareName){
          return false;
    }

    public long getCurrentShareRate(String shareName){
           return 0;
    }


    public Share[] getAllSharesAsSnapShot(){
          return new Share[0];
    }

    protected void updateShareRates(){

    }

    protected void updateShareRates(Share share){

    }

    public void startUpdate(){

    }

    public Share getShare(String name){
          return new Share("dummy", 0);
    }
}
