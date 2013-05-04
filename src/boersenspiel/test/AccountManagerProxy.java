package boersenspiel.test;

import boersenspiel.interfaces.AccountManager;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 04.05.13
 * Time: 10:38
 */
public class AccountManagerProxy implements AccountManager{

    private AccountManager accountManager;
    private static Logger logger = Logger.getLogger(AccountManagerProxy.class.getName());

    public long getShareDepositValue(String name) {
        logger.fine("Account Manager gets Cash Account Value");
        return accountManager.getCashAccountValue(name);
    }

    public void sell(String name, String shareName, int amount) throws Exception {
        accountManager.sell(name, shareName, amount);
    }

    public void createPlayer(String name, long cash) {
        accountManager.createPlayer(name, cash);
    }

    public void buy(String name, String shareName, int amount) throws Exception {
        accountManager.buy(name, shareName, amount);
    }

    public long getCashAccountValue(String name) {
        return accountManager.getCashAccountValue(name);
    }

    public long getAssetValue(String name) {
        return accountManager.getAssetValue(name);
    }
}
