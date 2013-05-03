package boersenspiel.launcher;

import boersenspiel.gui.StockPriceViewer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.provider.RandomStockPriceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 02.05.13
 * Time: 17:42
 */
public class StockGameLauncher {

    public static void main(String [] args) throws Exception {
        RandomStockPriceProvider rnd = new RandomStockPriceProvider();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(ShareManagement.getInstance(),rnd);
        rnd.startUpdate();
        stockPriceViewer.updateInfo();

        ShareManagement.getInstance().addShare("BMW", 100);
        ShareManagement.getInstance().addShare("Siemens", 150);

        AccountManager accountManager = new AccountManagerImpl();
        StockGameCommandProcessor cmp = new StockGameCommandProcessor(accountManager);
        cmp.process();
    }

}
