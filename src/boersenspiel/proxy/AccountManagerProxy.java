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




