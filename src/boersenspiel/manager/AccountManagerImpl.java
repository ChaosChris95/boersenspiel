package boersenspiel.manager;

import boersenspiel.account.Player;
import boersenspiel.account.PlayerAgent;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.interfaces.AccountManager;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:34
 */
public class AccountManagerImpl implements AccountManager {

    private UserManagement userManagement;
    private ShareManagement shareManagement;
    private PlayerAgent playerAgent;

    public AccountManagerImpl() {
        this.shareManagement = ShareManagement.getInstance();
        this.userManagement = UserManagement.getInstance();
    }

    @Override
    public void createPlayer(String name, long cash) {
        userManagement.addPlayer(name, cash);
    }

    public void botPlayer(String name) {
        this.playerAgent = new PlayerAgent(userManagement.getPlayer(name));
    }

    public Player getPlayer(String name) {
        return userManagement.getPlayer(name);
    }

    @Override
    public void buy(String playerName, String shareName, int amount) throws NotEnoughMoneyException, Exception{
        long shareValue = shareManagement.getSpecificRate(shareName);
        shareValue *= amount;
        userManagement.getPlayer(playerName).buy(shareManagement.getShare(shareName), amount);
    }

    @Override
    public void sell(String playerName, String shareName, int amount) throws Exception {
        long shareValue = shareManagement.getSpecificRate(shareName);
        shareValue *= amount;
        userManagement.getPlayer(playerName).sell(shareManagement.getShare(shareName), amount);
    }

    @Override
    public long getCashAccountValue(String playerName) {
        return userManagement.getPlayer(playerName).getCashAccountValue();
    }

    @Override
    public long getShareDepositValue(String playerName) {
        return userManagement.getPlayer(playerName).getShareDepositValue();
    }

    @Override
    public long getAssetValue(String name) {
        return (getCashAccountValue(name) + getShareDepositValue(name));
    }

    public String getStock(String name) {
        return userManagement.getPlayer(name).getStockList();
    }
}
