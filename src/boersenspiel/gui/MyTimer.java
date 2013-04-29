package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 29.04.13
 * Time: 13:40
 */

import java.util.Timer;

class MyTimer {

    private MyTimer(){
        Timer timer = new Timer();
        timer = new Timer(true); //as daemon
        //timer.scheduleAtFixedRate(new TickerTask(), 1000, TICK_PERIOD);
    }

    private static MyTimer instance = null;

    private static final int TICK_PERIOD = 1000;
    private java.util.Timer ticker;

    public static MyTimer getInstance()
    {
        if (instance == null) {
            instance = new MyTimer();
        }
        return instance;
    }

}