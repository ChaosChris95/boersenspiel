package boersenspiel.launcher;

import boersenspiel.account.Player;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.manager.UserManagement;
import boersenspiel.provider.RandomStockPriceProvider;
import boersenspiel.gui.StockPriceViewer;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 14:00
 */


public class TestRunner {
	
	public static void main(String[] args) throws Exception {

        ShareManagement shareManagement = new ShareManagement();
        UserManagement userManagement = new UserManagement();

        shareManagement.addShare("BMW", 100);
        shareManagement.addShare("Siemens", 150);


        Player one = new Player("Bianca");
        one.buy(shareManagement.getShare("BMW"), 5);

        RandomStockPriceProvider randomStockPriceProvider = new RandomStockPriceProvider(shareManagement, userManagement);
        StockPriceViewer stockPriceViewer = new StockPriceViewer(shareManagement, randomStockPriceProvider);
        stockPriceViewer.updateInfo();

        AccountManager accountManager = new AccountManagerImpl(shareManagement);

        /*try {
            shareManagement.addShare("BMW", 100);
        } catch (ShareNameAlreadyExistsException e) {
            return;
        }*/

	}
}
