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

        /*Player one = new Player("Bianca");
        one.addCash(40000);
        System.out.println(one.toString());

        ShareManagement shareManagement = new ShareManagement();
        shareManagement.addShare("BMW", 100);
        shareManagement.addShare("Audi", 200);

        System.out.println(one.getCashAccountValue());
        one.addShareToDeposit(shareManagement.getShare("BMW"), 15);
        System.out.println(one.getCashAccountValue());
        System.out.println(one.getShareDepositValue());
        System.out.println(one.getStockList());
        one.subShareFromDeposit(shareManagement.getShare("BMW"), 5);
        System.out.println(one.getCashAccountValue());
        System.out.println(one.getShareAmount("BMW"));


        AccountManager accountManager = new AccountManagerImpl(shareManagement);


        System.out.println(accountManager.getList());
        RandomStockPriceProvider randomStockPriceProvider = new RandomStockPriceProvider(shareManagement);
        System.out.println(shareManagement.getShare("BMW").getPrice());
        randomStockPriceProvider.updateShareRate(shareManagement.getShare("BMW"));
        System.out.println(shareManagement.getShare("BMW").getPrice());
        randomStockPriceProvider.updateShareRates();
        System.out.println(shareManagement.getShare("BMW").getPrice());*/

        ShareManagement shareManagement = new ShareManagement();
        UserManagement userManagement = new UserManagement();

        shareManagement.addShare("BMW", 100);
        shareManagement.addShare("Siemens", 150);
        //shareManagement.addShare("BMW", 200);


        Player one = new Player("Bianca");
        one.buy(shareManagement.getShare("BMW"), 5);


        RandomStockPriceProvider randomStockPriceProvider = new RandomStockPriceProvider(shareManagement, userManagement);
        randomStockPriceProvider.updateShareRate(shareManagement.getShare("BMW"));
        System.out.println(shareManagement.getSpecificRate("BMW"));



        /*StockPriceViewer stockPriceViewer = new StockPriceViewer(shareManagement, randomStockPriceProvider);
        stockPriceViewer.start();*/



        randomStockPriceProvider.startUpdate();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(shareManagement);
        stockPriceViewer.updateInfo();

        AccountManager accountManager = new AccountManagerImpl(shareManagement);

        try {
            shareManagement.addShare("BMW", 100);
        } catch (ShareNameAlreadyExistsException e) {
            return;
        }

	}
}
