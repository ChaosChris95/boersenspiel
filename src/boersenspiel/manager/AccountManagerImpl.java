package boersenspiel.manager;

import boersenspiel.account.LogEntry;
import boersenspiel.account.Player;
import boersenspiel.account.PlayerAgent;
import boersenspiel.exceptions.*;
import boersenspiel.gui.UpdateTimer;
import boersenspiel.interfaces.AccountManager;

import java.util.Date;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:34
 */

public class AccountManagerImpl implements AccountManager {

    private static Logger logger = Logger.getLogger("AccountManagerImpl");

    private static AccountManagerImpl instance = null;
    public static AccountManagerImpl getInstance() {
        if(AccountManagerImpl.instance == null) {
            AccountManagerImpl.instance = new AccountManagerImpl();
        }
        return AccountManagerImpl.instance;
    }

    private UserManagement userManagement;
    private ShareManagement shareManagement;
    private PlayerAgent playerAgent;

    public AccountManagerImpl() {
        this.shareManagement = ShareManagement.getInstance();
        this.userManagement = UserManagement.getInstance();
    }

    @Override
    public void createPlayer(String name, Long cash) throws NegativeValueException{
        try {
            userManagement.addPlayer(name, cash);
            logger.info("Spieler " + name + " erstellt mit einem Accountwert von " + cash);
        } catch (PlayerAlreadyExistsException e) {
            logger.warning("Der Spieler existiert bereits");
        } catch (NegativeValueException e) {
            logger.warning("Negativer Wert nicht erlaubt");
        }
    }

    public void botPlayer(String name) {
        try {
            this.playerAgent = new PlayerAgent(userManagement.getPlayer(name));
            UpdateTimer.getInstance().addTask(this.playerAgent.getTask(), 10000, 10000);
            logger.info("Stelle" + name + " um auf Bot");
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }

    }

    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        return userManagement.getPlayer(name);

    }

    @Override
    public void buy(String playerName, String shareName, Integer amount) {
        try {
            userManagement.getPlayer(playerName).buy(shareManagement.getShare(shareName), amount);
            logger.info("Spieler " + playerName + " kaufte " + amount + " Aktien von " + shareName);
        } catch (NotEnoughMoneyException e) {
            logger.warning("Sie besitzen nicht genug Geld.");
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }

    }

    @Override
    public void sell(String playerName, String shareName, Integer amount) throws NegativeValueException{
        try {
            userManagement.getPlayer(playerName).sell(shareManagement.getShare(shareName), amount);
            logger.info("Spieler " + playerName + " verkaufte " + amount + " Aktien von " + shareName);
        } catch (NotEnoughSharesException e) {
            logger.warning("Sie besitzen nicht genug Anzahl dieser Aktien");
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }

    }

    @Override
    public long getCashAccountValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getCashAccountValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return 0;
    }

    @Override
    public long getShareDepositValue(String playerName) {
        try {
            return userManagement.getPlayer(playerName).getShareDepositValue();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return 0;
    }

    @Override
    public long getAssetValue(String name) {
        return (getCashAccountValue(name) + getShareDepositValue(name));
    }

    public String getStock(String name) {
        try {
            return userManagement.getPlayer(name).getStockList();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return null;
    }

    public String getLogByShare(String name) {
        try {
            return userManagement.getPlayer(name).printByShare();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return null;
    }

    public String getLogByDate(String name) {
        try {
            return userManagement.getPlayer(name).printByDate();
        } catch (PlayerDoesNotExistException e) {
            logger.warning("Spieler existiert nicht");
        }
        return null;
    }


}
