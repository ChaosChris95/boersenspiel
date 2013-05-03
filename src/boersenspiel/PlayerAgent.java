package boersenspiel;

import boersenspiel.account.Player;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

import java.text.DateFormat;
import java.util.*;

/**
 * User: Peach
 * Date: 29.04.13
 * Time: 13:22
 */

public class PlayerAgent {

    Player player;

    public PlayerAgent(Player player) {
        this.player = player;

    }

    /*public void setPlayer(Player player) {
       this.player = player;
    }
    */

    public void sell(Share share, int amount) {
        player.sell(share, amount);
    }

    public void buy(Share share, int amount) {
        player.buy(share, amount);
    }

    public void algorithm() {
        int n;
        Share s;
        while(player.getCashAccountValue() > 0) {
            n = (int)(Math.random() * (ShareManagement.getInstance().getShareLength() - 1));
            s = ShareManagement.getInstance().getShareByNumber(n);
            System.out.println("Kaufe 5 Aktien von " +  s.getName());
            player.buy(s, 5);

            n = (int)(Math.random() * (ShareManagement.getInstance().getShareLength() - 1));
            s = ShareManagement.getInstance().getShareByNumber(n);
            System.out.println("Verkaufe 3 Aktien von " +  s.getName());
            player.sell(s, 3);
            break;
        }
    }

    public TimerTask getTask() {
        return new BotTask();
    }

    private class BotTask extends TimerTask{
        public void run(){
            algorithm();
        }
    }
}
