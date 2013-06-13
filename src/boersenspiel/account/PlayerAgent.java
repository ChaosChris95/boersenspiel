package boersenspiel.account;

import boersenspiel.account.Player;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

import java.text.DateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 29.04.13
 * Time: 13:22
 */

public class PlayerAgent {

    Player player;
    private static Logger logger = Logger.getLogger(PlayerAgent.class.getName());
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    /**
     * Constructor for PlayerAgent, set Player as PlayerAgent
     * @param player Player Object
     */

    public PlayerAgent(Player player) {
        this.player = player;

    }

    /**
     * Algorithm for PlayerAgent to buy and sell Shares randomly with Math.random and check
     * if enough shares are there and whether player is brocken or not.
     */

    public void algorithm() {
        int n;
        Share s;
        while(player.getCashAccountValue() > 0) {
            n = (int)(Math.random() * (ShareManagement.getInstance().getShareLength() - 1));
            s = ShareManagement.getInstance().getShareByNumber(n);
            logger.info(rs.getString("AgentBuy") + " " +  s.getName());
            try {
                player.buy(s, 5);
            } catch (NotEnoughMoneyException e) {
            } catch (NegativeValueException e) {} catch (Exception e) {
                e.printStackTrace();
            }

            n = (int)(Math.random() * (ShareManagement.getInstance().getShareLength() - 1));
            s = ShareManagement.getInstance().getShareByNumber(n);
            logger.info(rs.getString("AgentSell") + " " +  s.getName());
            try {
                player.sell(s, 3);
            } catch (NotEnoughSharesException e) {
            } catch (NegativeValueException e) {}
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
