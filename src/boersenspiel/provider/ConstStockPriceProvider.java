package boersenspiel.provider;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:07
 */

import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;
import boersenspiel.provider.StockPriceProvider;

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