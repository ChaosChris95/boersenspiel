package boersenspiel.gui;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 13:56
 */

import boersenspiel.manager.ShareManagement;
import boersenspiel.provider.StockPriceProvider;

import java.util.*;
import java.text.DateFormat;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StockPriceViewer extends JFrame{

    private ShareManagement shareManagement;
    StockPriceProvider stockPriceProvider;
    private JLabel clockLabel;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public StockPriceViewer(ShareManagement shareManagement, StockPriceProvider stockPriceProvider){
        this.shareManagement = shareManagement;
        this.stockPriceProvider = stockPriceProvider;
        clockLabel = new JLabel();
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public void updateInfo() {
        TickerTask tickerTask = new TickerTask();
        UpdateTimer.getInstance().addTask(tickerTask, 1000, 1000);
    }

    private class TickerTask extends TimerTask{

        public TickerTask() {
              run();
        }

        public void run(){
            clockLabel.setText(createText());
            clockLabel.repaint();
        }

        private String createText(){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<html><body>" + rs.getString("GuiAktie") + " :<br>");
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.getDefault());
            stringBuffer.append(dateFormatter.format(date));
            stringBuffer.append("<br>");
            stringBuffer.append(shareManagement.getSharesAndRates());
            stringBuffer.append("</body></html>");
            return stringBuffer.toString();
        }
    }
}
