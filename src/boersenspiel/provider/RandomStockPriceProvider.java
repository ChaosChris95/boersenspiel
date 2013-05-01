package boersenspiel.provider;

/**
* Created with IntelliJ IDEA.
* User: jan
* Date: 15.04.13
* Time: 14:10
*/

import boersenspiel.gui.UpdateTimer;
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
    public void startUpdate(){
        UpdateShareTimer myTimer = new UpdateShareTimer();
        myTimer.startTiming();
    }

    class UpdateShareTimer {
        private void startTiming(){
            UpdateTimer.getInstance(new TimerTask() {
                public void run() {
                        RandomStockPriceProvider.this.updateShareRates();
                }
            });
        }
    }
}
