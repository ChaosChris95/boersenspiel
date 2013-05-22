package boersenspiel.launcher;

import boersenspiel.AccountManagerProxy;
import boersenspiel.gui.StockPriceViewer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.RandomStockPriceProvider;
import boersenspiel.shell.InvocationHandler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 02.05.13
 * Time: 17:01
 */

public class StockGameLauncher {

    public static void main(String [] args) throws Exception {

        LogManager.getLogManager().readConfiguration(new BufferedInputStream(new FileInputStream("c:/logging.properties")));

        RandomStockPriceProvider rnd = new RandomStockPriceProvider();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(ShareManagement.getInstance(),rnd);

        //loading Proxy
        AccountManager proxy = (AccountManager) Proxy.newProxyInstance(AccountManager.class.getClassLoader(),
                new Class<?>[] {AccountManager.class},
                new InvocationHandler(AccountManagerImpl.getInstance()));


        rnd.startUpdate();
        stockPriceViewer.updateInfo();

        ShareManagement.getInstance().addShare("BMW", 100L);
        ShareManagement.getInstance().addShare("Siemens", 150L);
        ShareManagement.getInstance().addShare("Xaver", 100L);
        ShareManagement.getInstance().addShare("Martin", 150L);
        ShareManagement.getInstance().addShare("Harribert", 100L);
        ShareManagement.getInstance().addShare("Lotto", 150L);

        AccountManagerImpl accountManager = AccountManagerImpl.getInstance();
        //AccountManagerProxy accountManagerProxy = new AccountManagerProxy(accountManager);
        StockGameCommandProcessor cmp = new StockGameCommandProcessor(proxy);
        cmp.process();
    }

}
