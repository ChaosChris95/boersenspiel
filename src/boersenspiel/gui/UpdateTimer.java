package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 29.04.13
 * Time: 13:40
 */

import java.util.Timer;
import java.util.TimerTask;

public class UpdateTimer {

    private TimerTask whatToDo = null;
    private static final int TICK_PERIOD = 1000;


    private UpdateTimer(TimerTask wtd){
        Timer timer = new Timer(true); //as daemon
        timer.scheduleAtFixedRate( wtd, 1000, TICK_PERIOD);
    }

    private static UpdateTimer instance = null;


    public static UpdateTimer getInstance(TimerTask todo)
    {
        if (instance == null) {
            instance = new UpdateTimer( todo );
        }
        return instance;
    }



}