package boersenspiel;

import boersenspiel.account.Player;
import boersenspiel.stock.Share;

import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 29.04.13
 * Time: 12:54
 */

public class playerAgent {

    Player player;

    public playerAgent(Player player) { //todo timer
        this.player = player;
    }

    public void sell(Share share, int amount) {
        player.sell(share, amount);
    }

    public void buy(Share share, int amount) {
        player.buy(share, amount);
    }

}
