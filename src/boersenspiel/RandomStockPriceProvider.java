package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.math.*;

public class RandomStockPriceProvider extends StockPriceProvider {

    private ShareManagement shareManagement;
    private long max = 50;
    private long min = -50;

    public RandomStockPriceProvider(ShareManagement shareManagement){
        super(shareManagement);
    }

    protected void updateShareRates(){

        long erg=0;

        for (int i=0; i<shareManagement.getShareLength(); i++){
            erg =(long)Math.round(Math.random() * (max - min + 1)+ min);
            shareManagement.getShareNumber(i).increasePrice(erg);
        }

    }

    protected void updateShareRate(Share share){
        shareManagement.getShare(share.getName()).increasePrice((long)Math.round(Math.random() * (max - min + 1)+ min));
    }

    public void startUpdate(){
        updateShareRates();

    }

    private void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                RandomStockPriceProvider.this.startUpdate();
            }
        }, 2000, 1000);
    }
}
