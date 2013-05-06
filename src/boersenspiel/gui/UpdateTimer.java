package boersenspiel.gui;

/**
 * User: jan
 * Date: 29.04.13
 * Time: 13:40
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateTimer {

    private static UpdateTimer instance = null;
    public static UpdateTimer getInstance() {
        if(UpdateTimer.instance == null) {
            UpdateTimer.instance = new UpdateTimer();
        }
        return UpdateTimer.instance;
    }

    private static final int TICK_PERIOD = 1000;
    private Timer timer = new Timer();

    private UpdateTimer() {
    }

    public void addTask(TimerTask wtd, int delay, int period) {
        timer.scheduleAtFixedRate(wtd, delay, period);
    }




}