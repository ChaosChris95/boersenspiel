package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */

import java.util.Timer;
import java.util.TimerTask;

public class ConstStockPriceProvider extends StockPriceProvider {

    public ConstStockPriceProvider(ShareManagement shareManagement){
        super(shareManagement);
    }

    protected void updateShareRates(){
        return;

    }

    protected void updateShareRate(Share share){
        return;
    }

    public void startUpdate(){
        return;

    }
}