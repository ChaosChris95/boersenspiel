package boersenspiel;

/**
 * User: jan
 * Date: 04.05.13
 * Time: 10:38
 */

import boersenspiel.interfaces.AccountManager;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class AccountManagerProxy implements AccountManager{

    private AccountManager accountManager;
    private static Logger logger = Logger.getLogger(AccountManagerProxy.class.getName());

    public AccountManagerProxy(AccountManager accountManager) throws IOException {
        this.accountManager = accountManager;
        Handler handler = new FileHandler( "log.txt" );
        logger.addHandler(handler);
        handler.setLevel(Level.FINE);
        logger.setLevel(Level.INFO);
    }

    public long getShareDepositValue(String name) {
        logger.fine("AccountManagerProxy: getShareDepositValue(" + name + ")");
        return accountManager.getCashAccountValue(name);
    }

    public void sell(String name, String shareName, Integer amount) throws Exception {
        logger.fine("AccountManagerProxy: sell(" + name + "," + shareName + "," + amount + ")");
        accountManager.sell(name, shareName, amount);
    }

    public void createPlayer(String name, Long cash) {
        logger.fine("AccountManagerProxy: createPlayer(" + name + "," + cash + ")");
        accountManager.createPlayer(name, cash);
    }

    public void buy(String name, String shareName, Integer amount) throws Exception {
        logger.fine("AccountManagerProxy: buy(" + name + "," + shareName + ", " + amount + ")");
        accountManager.buy(name, shareName, amount);
    }

    public long getCashAccountValue(String name) {
        logger.fine("AccountManagerProxy: getAccountValue(" + name + ")");
        return accountManager.getCashAccountValue(name);
    }

    public long getAssetValue(String name) {
        logger.fine("AccountManagerProxy: getAssetValue(" + name + ")");
        return accountManager.getAssetValue(name);
    }

    public String getStock(String name) {
        logger.fine("AccountManagerProxy: getStock(" + name + ")");
        return accountManager.getStock(name);
    }

    public void botPlayer(String name) {
        logger.fine("AccountManagerProxy: getAssetValue(" + name + ")");
        accountManager.botPlayer(name);
    }
}
