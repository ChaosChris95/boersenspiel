package boersenspiel.provider;

/**
* Created with IntelliJ IDEA.
* User: jan
* Date: 15.04.13
* Time: 14:10
*/

import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

import java.util.Timer;
import java.util.TimerTask;

public class RandomStockPriceProvider extends StockPriceProvider {

    private ShareManagement shareManagement;
    private long max = 50;
    private long min = -50;

    public RandomStockPriceProvider(ShareManagement shareManagement){
        super(shareManagement);
    }

    public void updateShareRates() throws NullPointerException{

        long erg=0;

        try {
            for (int i=0; i<shareManagement.getShareLength(); i++){
                erg =(long)Math.round(Math.random() * (max - min + 1)+ min);
                shareManagement.getShareNumber(i).increasePrice(erg);
            }
        }catch (NullPointerException f){}
    }

    public void updateShareRate(Share share) throws NullPointerException{

        long erg=0;

        erg = (long)Math.round(Math.random() * (max - min + 1)+ min);
        try{
            shareManagement.getShare(share.getName()).increasePrice(erg);
        } catch (NullPointerException e){}
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
