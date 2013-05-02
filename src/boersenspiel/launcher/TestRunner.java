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

        ShareManagement shareManagement = ShareManagement.getInstance();
        UserManagement userManagement = UserManagement.getInstance();

        shareManagement.addShare("BMW", 100);
        shareManagement.addShare("Siemens", 150);


        Player one = new Player("Bianca");
        one.buy(shareManagement.getShare("BMW"), 5);

        RandomStockPriceProvider randomStockPriceProvider = new RandomStockPriceProvider();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(shareManagement);
        stockPriceViewer.start();

        AccountManager accountManager = new AccountManagerImpl();

        /*try {
            shareManagement.addShare("BMW", 100);
        } catch (ShareNameAlreadyExistsException e) {
            return;
        }*/

	}
}
