package boersenspiel.proxy;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.interfaces.AccountManager;

import java.lang.reflect.Proxy;

/**
 * User: Jan
 * Date: 22.05.13
 * Time: 10:02
 */

public class AccountManagerClient {

    public AccountManagerClient(AccountManager accountManager) throws NegativeValueException{
        AccountManagerProxy handler = new AccountManagerProxy(accountManager);
        AccountManagerProxy accountManagerProxy = new AccountManagerProxy(accountManager);
        AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
                AccountManager.class.getClassLoader(), new Class[]{AccountManager.class}, handler);
        startTest(proxy);
    }

    public void startTest(AccountManager proxy)throws NegativeValueException{
        proxy.createPlayer("Mr.Burns", 1000000L);
    }
}
