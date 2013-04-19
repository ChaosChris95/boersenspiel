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

    public ConstStockPriceProvider(){
        super();
    }

    protected void updateShareRate(Share share){

    }

    public void startUpdate(){

    }

}

/*
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo  {
    private int counter;

    private void modifyUserObject() {
        System.out.println("modifyUserObject: " + ++counter);
    }

    private void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                TimerDemo.this.modifyUserObject();
            }
        }, 2000, 1000);
    }

    public static void main(String[] args)  {
        new TimerDemo().startTiming();
    }
}
 */
