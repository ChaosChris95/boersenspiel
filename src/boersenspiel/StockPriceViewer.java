package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 15.04.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StockPriceViewer extends JFrame{

    private static final int TICK_PERIOD = 1000;
    private Timer ticker;
    private JLabel clockLabel;
    private static StockPriceProvider stockPriceProvider = new StockPriceProvider();

    private class TickerTask extends TimerTask {
        public void run() {
            String output = createText();
            clockLabel.setText(output);
            clockLabel.repaint();
        }

        private String createText() {
            String output = "<html><body>hallo <br> welt <br>";
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            output += dateFormatter.format(date) + "</body></html>";
            return output;
        }
    }


    public StockPriceViewer() {
        clockLabel = new JLabel("coming soon ...");
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100, 100);
        setVisible(true);

    }

    public void start() {
        ticker = new Timer(true); //as daemon
        ticker.scheduleAtFixedRate(new TickerTask(), 1000, TICK_PERIOD);
    }

    /*public static void main(String[] args) {
        StockPriceProvider stockPriceProvider = new StockPriceViewer(stockPriceProvider);
        stockPriceViewer.start();
    }*/
}