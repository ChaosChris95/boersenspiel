package boersenspiel.provider;

/**
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

public class RandomStockPriceProvider extends StockPriceProvider {

    private double max = 50;
    private double min = -50;
    public UpdateTimer timer = UpdateTimer.getInstance();

    public RandomStockPriceProvider(){
        super();
    }

    /**
     * updates the rate of all existing Shares randomly
     * @throws NegativeValueException if price of a share will become a negative number
     */

    public void updateShareRates() throws NegativeValueException {

        double erg=0;

        for (int i=0; i<shareManagement.getShareLength(); i++){
            if (shareManagement.getShareByNumber(i) == null)
                continue;
            erg = Math.round(Math.random() * (max - min + 1)+ min);
                shareManagement.getShareByNumber(i).increasePrice((long)erg);
        }
    }

    /**
     * updates the rate of a single existing Share randomly
     */

    public void updateShareRate(Share share){

        double erg=0;

        erg = (long)Math.round(Math.random() * (max - min + 1)+ min);
        shareManagement.getShare(share.getName()).increasePrice((long)erg);
    }

    /**
     * updates the shares every seconds on display
     */

    public void startUpdate(){
         timer.addTask(new TimerTask() {
             public void run() {
                 try {
                     updateShareRates();
                 } catch (NegativeValueException e) {}
             }
         }, 1000, 1000);
    }
}
