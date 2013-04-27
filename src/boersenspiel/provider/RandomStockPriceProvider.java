package boersenspiel.provider;

/**
* Created with IntelliJ IDEA.
* User: jan
* Date: 15.04.13
* Time: 14:10
*/

import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.stock.Share;
import boersenspiel.exceptions.NegativeValueException;

import java.util.Timer;
import java.util.TimerTask;
import java.lang.Math;

public class RandomStockPriceProvider extends StockPriceProvider {  //TODO Where is the buck

    private double max = 50;
    private double min = -50;

    public RandomStockPriceProvider(ShareManagement shareManagement, UserManagement userManagement){
        super(shareManagement, userManagement);
    }

    public void updateShareRates() throws NegativeValueException {

        double erg=0;

        for (int i=0; i<shareManagement.getShareLength(); i++){
            if (shareManagement.getShareByNumber(i) == null)
                continue;
            erg = Math.round(Math.random() * (max - min + 1)+ min);
            System.out.println(erg);           //TODO comment out later - just debugger
            try{
                shareManagement.getShareByNumber(i).increasePrice((long)erg);
            } catch (NegativeValueException e){
                return;
            }
        }
    }

    public void updateShareRate(Share share){

        double erg=0;

        erg = (long)Math.round(Math.random() * (max - min + 1)+ min);
        shareManagement.getShare(share.getName()).increasePrice((long)erg);
    }

    @Override
    public void startUpdate()  throws Exception{
        MyTimer myTimer = new MyTimer();
        myTimer.startTiming();
    }

    class MyTimer {
        private void startTiming() throws Exception{
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run(){
                    try{
                        RandomStockPriceProvider.this.updateShareRates();
                    } catch (Exception e){}
                }
            }, 2000, 1000);
        }
    }
}
