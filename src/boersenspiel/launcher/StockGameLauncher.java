package boersenspiel.launcher;

import boersenspiel.gui.MainWindow;
import boersenspiel.proxy.AccountManagerClient;
import boersenspiel.gui.StockPriceViewer;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.HistoricalStockPriceProvider;
import boersenspiel.shell.InvocationHandler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.LogManager;

/**
 * User: Peach
 * Date: 02.05.13
 * Time: 17:01
 */

public class StockGameLauncher {

    public static void main(String [] args) throws Exception {

        LogManager.getLogManager().readConfiguration(new BufferedInputStream(new FileInputStream("c:\\logging.properties")));

        String lang = System.getProperty("lang");
        if (lang == null) {
           Locale.setDefault(Locale.GERMAN);
        } else {
            if (lang.equals("en")) {
                Locale.setDefault(Locale.ENGLISH);
            } else {
                Locale.setDefault(Locale.GERMAN);
            }
        }

        ShareManagement.getInstance().addShare("Apple", 100L);
        ShareManagement.getInstance().addShare("BMW", 100L);
        ShareManagement.getInstance().addShare("DeutscheBank", 100L);
        ShareManagement.getInstance().addShare("KUKA", 100L);
        ShareManagement.getInstance().addShare("Siemens", 100L);
        ShareManagement.getInstance().addShare("Volkswagen", 100L);
        ShareManagement.getInstance().addShare("Yahoo", 100L);


        //RandomStockPriceProvider rnd = new RandomStockPriceProvider();
        HistoricalStockPriceProvider rnd = new HistoricalStockPriceProvider();
        rnd.startUpdate();
        StockPriceViewer stockPriceViewer = new StockPriceViewer(ShareManagement.getInstance(), rnd);

        //loading Proxy
        AccountManager proxy = (AccountManager) Proxy.newProxyInstance(AccountManager.class.getClassLoader(),
                new Class<?>[] {AccountManager.class},
                new InvocationHandler(AccountManagerImpl.getInstance()));

        //rnd.startUpdate();
        stockPriceViewer.updateInfo();

        StockGameCommandProcessor cmp = new StockGameCommandProcessor(proxy);
        cmp.process();

        //MainWindow mainWindow = new MainWindow(args);
    }

}
