package boersenspiel;

import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import org.omg.CORBA.portable.InvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: Jan
 * Date: 22.05.13
 * Time: 10:02
 */

public class AccountManagerClient {

    public AccountManagerClient(AccountManager accountManager){
        AccountManagerProxy handler = new AccountManagerProxy(accountManager);
        AccountManagerProxy accountManagerProxy = new AccountManagerProxy(accountManager);
        AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
                AccountManager.class.getClassLoader(), new Class[]{AccountManager.class}, handler);
        startTest(proxy);
    }

    public void startTest(AccountManager proxy){
        proxy.createPlayer("Mr. Burns", 1000000L);
    }
}
