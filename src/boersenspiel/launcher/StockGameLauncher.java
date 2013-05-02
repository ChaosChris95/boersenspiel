package boersenspiel.launcher;

import boersenspiel.gui.StockPriceViewer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.provider.RandomStockPriceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 25.04.13
 * Time: 11:19
 */
public class StockGameLauncher {

    public static void main(String [] args) throws Exception {
        RandomStockPriceProvider rnd = new RandomStockPriceProvider(ShareManagement.getInstance(), UserManagement.getInstance());
        StockPriceViewer stockPriceViewer = new StockPriceViewer(ShareManagement.getInstance());
        stockPriceViewer.start();

        ShareManagement.getInstance().addShare("BMW", 100);
        ShareManagement.getInstance().addShare("Siemens", 150);

        AccountManager accountManager = new AccountManagerImpl(ShareManagement.getInstance());
        StockGameCommandProcessor cmp = new StockGameCommandProcessor(accountManager);
        cmp.process();
    }

}
