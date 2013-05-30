package boersenspiel.proxy;

/**
 * User: jan
 * Date: 04.05.13
 * Time: 10:38
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;

//TODO Location Handler

public class AccountManagerProxy implements InvocationHandler{

    Logger logger = Logger.getLogger("AccountManagerProxy");
    private AccountManager accountManager;

    public AccountManagerProxy(AccountManager accountManager){
        this.accountManager = accountManager;
    }

    public Object invoke(Object proxy, Method method, Object[] args){
        logger.fine("* calling method " + method + " with params ");
        for (int i = 0; i < args.length; i++)
            logger.fine(" " + args[i]);
        //logger.fine("\n");

        Object result = null;
        try  {
            result = method.invoke(accountManager, args);
        } catch(IllegalAccessException ex)  {
        } catch(InvocationTargetException ex)  {
            logger.warning("* exception:" + ex.getTargetException());
            //throw ex.getTargetException();
        }
        logger.fine("* result:" + result);
        return result;
    }
}

/*
import boersenspiel.account.LogEntry;
import boersenspiel.account.Player;
import boersenspiel.exceptions.PlayerDoesNotExistException;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class AccountManagerProxy {

    private AccountManagerImpl accountManager;
    private ShareManagement shareManagement;
    private static Logger logger = Logger.getLogger(AccountManagerProxy.class.getName());

    AccountManager proxy = (AccountManager) Proxy.newProxyInstance(AccountManager.class.getClassLoader(),
            new Class<?>[] {AccountManager.class},
            new boersenspiel.shell.InvocationHandler(AccountManagerImpl.getInstance()));


    public AccountManagerProxy(AccountManagerImpl accountManager) throws IOException {
        this.accountManager = accountManager;
        Handler handler = new FileHandler( "log.txt" );
        logger.addHandler(handler);
        handler.setLevel(Level.FINE);
        logger.setLevel(Level.FINE);
    }

    public long getShareDepositValue(String name) {
        logger.fine("AccountManagerProxy: getShareDepositValue(" + name + ")");
        return accountManager.getCashAccountValue(name);
    }

    public void sell(String name, String shareName, Integer amount) throws Exception {
        logger.fine("AccountManagerProxy: sell(" + name + "," + shareName + "," + amount + ")");
        //accountManager.getPlayer(name).addLogEntry(new LogEntry(new Date(), shareManagement.getShare(shareName), amount));
        accountManager.sell(name, shareName, amount);

    }

    /*public void createPlayer(String name, Long cash) {
        logger.fine("AccountManagerProxy: createPlayer(" + name + "," + cash + ")");
        proxy.createPlayer(name, cash);
    }


    public void createPlayer(String name, Long cash) {
        logger.fine("AccountManagerProxy: createPlayer(" + name + "," + cash + ")");
        accountManager.createPlayer(name, cash);
    }

    public void buy(String name, String shareName, Integer amount) throws Exception {
        logger.fine("AccountManagerProxy: buy(" + name + "," + shareName + ", " + amount + ")");
        //accountManager.getPlayer(name).addLogEntry(new LogEntry(new Date(), shareManagement.getShare(shareName), amount));
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
}*/

