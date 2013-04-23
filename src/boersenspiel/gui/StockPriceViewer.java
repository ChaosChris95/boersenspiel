package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 13:56
 */

import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.RandomStockPriceProvider;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StockPriceViewer extends JFrame{

    private ShareManagement shareManagement;
    private RandomStockPriceProvider randomStockPriceProvider;

    private static final int TICK_PERIOD = 1000;
    private Timer ticker;
    private JLabel clockLabel;

    private class TickerTask extends TimerTask{
        public void run(){
            String output = createText();
            clockLabel.setText(output);
            clockLabel.repaint();
        }

        private String createText(){
            String output = "<html><body>Die Verfügbaren Aktien mit ihrem Kurs:<br>";
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            output += dateFormatter.format(date);
            for (int i=0; i < 10; i++){     //dummy
                try{
                    randomStockPriceProvider.startUpdate(); //TODO right placement?
                } catch (Exception e){}
                output += shareManagement.getSharesAndRates();
            }
            output += "</body></html>";
            return output;
        }
    }


    public StockPriceViewer(ShareManagement shareManagement, RandomStockPriceProvider randomStockPriceProvider) throws Exception{
        this.shareManagement = shareManagement;
        this.randomStockPriceProvider = randomStockPriceProvider;
        TickerTask t = new TickerTask();
        clockLabel = new JLabel(t.createText());         //("coming soon ...");
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

    }

    public void start(){
        ticker = new Timer(true); //as daemon
        ticker.scheduleAtFixedRate(new TickerTask(), 1000, TICK_PERIOD);
    }
}