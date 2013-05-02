package boersenspiel;

import boersenspiel.account.Player;
import boersenspiel.stock.Share;

import java.util.Timer;

/**
 * User: Peach
 * Date: 29.04.13
 * Time: 13:22
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

    public void algorithm() {

    }

}
