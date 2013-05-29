package boersenspiel.launcher;

import boersenspiel.AccountManagerClient;
import boersenspiel.gui.StockPriceViewer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.HistoricalStockPriceProvider;
import boersenspiel.shell.InvocationHandler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.util.logging.LogManager;

/**
 * User: Peach
 * Date: 02.05.13
 * Time: 17:01
 */

public class StockGameLauncher {

    public static void main(String [] args) throws Exception {

        LogManager.getLogManager().readConfiguration(new BufferedInputStream(new FileInputStream("c:\\logging.properties")));

        ShareManagement.getInstance().addShare("Apple", 100L);
        ShareManagement.getInstance().addShare("BMW", 100L);
        ShareManagement.getInstance().addShare("DeutscheBank", 100L);
        ShareManagement.getInstance().addShare("KUKA", 100L);
        ShareManagement.getInstance().addShare("Siemens", 100L);
        ShareManagement.getInstance().addShare("Volkswagen", 100L);
        ShareManagement.getInstance().addShare("Yahoo", 100L);

        //RandomStockPriceProvider rnd = new RandomStockPriceProvider();
        HistoricalStockPriceProvider rnd = new HistoricalStockPriceProvider();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(ShareManagement.getInstance(), rnd);

        //loading Proxy
        AccountManager proxy = (AccountManager) Proxy.newProxyInstance(AccountManager.class.getClassLoader(),
                new Class<?>[] {AccountManager.class},
                new InvocationHandler(AccountManagerImpl.getInstance()));

        //rnd.startUpdate();
        stockPriceViewer.updateInfo();

        AccountManager accountManager = AccountManagerImpl.getInstance();
        AccountManagerClient accountManagerClient = new AccountManagerClient(accountManager);
        StockGameCommandProcessor cmp = new StockGameCommandProcessor(proxy);
        cmp.process();
    }

}
