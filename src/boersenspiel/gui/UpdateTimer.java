package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
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

    private UpdateTimer() {
    }

    public void addTask(TimerTask wtd) {
        Timer timer = new Timer(true); // as daemon
        timer.scheduleAtFixedRate(wtd, 1000, 1000);
    }




}