package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 13:56
 */

import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.RandomStockPriceProvider;

import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StockPriceViewer extends JFrame{

    private ShareManagement shareManagement;
    RandomStockPriceProvider randomStockPriceProvider;
    private JLabel clockLabel;

    public StockPriceViewer(ShareManagement shareManagement, RandomStockPriceProvider randomStockPriceProvider){
        this.shareManagement = shareManagement;
        this.randomStockPriceProvider = randomStockPriceProvider;
        clockLabel = new JLabel();
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public void updateInfo() {
        TickerTask tickerTask = new TickerTask();
        //UpdateTimer.getInstance().addTask(tickerTask);
    }

    private class TickerTask extends TimerTask{

        public TickerTask() {
            run();
        }

        public void run(){
            randomStockPriceProvider.startUpdate();
            clockLabel.setText(createText());
            clockLabel.repaint();
        }

        private String createText(){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<html><body>Die verfügbaren Aktien mit ihrem Kurs:<br>");
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            stringBuffer.append(dateFormatter.format(date));
            stringBuffer.append("<br>");
            stringBuffer.append(shareManagement.getSharesAndRates());
            stringBuffer.append("</body></html>");
            return stringBuffer.toString();
        }
    }
}
