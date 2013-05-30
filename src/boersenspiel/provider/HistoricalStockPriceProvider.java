package boersenspiel.provider;

import java.io.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Logger;

import boersenspiel.gui.UpdateTimer;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

/**
 * User: Jan
 * Date: 17.05.13
 * Time: 13:31
 */

public class HistoricalStockPriceProvider extends StockPriceProvider{

    Logger logger = Logger.getLogger("HistoricalStockPriceProvider");
    private ShareManagement shareManagement;
    public UpdateTimer timer = UpdateTimer.getInstance();
    private int counter = 1;    //TODO
    ArrayList<Long> prices;
    ArrayList<ArrayList<Long>> pricesOfShares;

    public HistoricalStockPriceProvider() throws IOException {
        shareManagement = ShareManagement.getInstance();
        readShareRates();
        startUpdate();
    }

    public ArrayList<Long> readShareRate(Share share) throws IOException {
        BufferedReader reader = null;
        try {
//            if (share != null)
//                logger.warning("Share: " +  share.getName() );
            File fileURL = new File(ClassLoader.getSystemResource("./Aktien/" + share.getName() + ".csv").toURI());
            reader = new BufferedReader(new FileReader(fileURL));
        }catch (FileNotFoundException e) { logger.warning("FileNotFoundException");
        }catch (Exception e) { logger.warning("Exception: " + e.toString()); }
        String line;
        prices = new ArrayList<Long>();
        boolean firstLine = true;
        try{
            while ((line = reader.readLine()) != null) {
                if (firstLine == true){
                    firstLine = false;
                    continue;
                }
                String[] splitLine = line.split(",");
                float erg = Float.parseFloat(splitLine[4]);
                prices.add((long)erg);
            }
            reader.close();
        } catch (NullPointerException e){};
        return prices;
    }

    public void readShareRates() throws IOException{
        pricesOfShares = new ArrayList<ArrayList<Long>>();
        for (int i=0; i<shareManagement.getShareLength(); i++){
            pricesOfShares.add(readShareRate(shareManagement.getShareByNumber(i)));
        }
    }

    public void updateShareRate(Share share){
        share.setPrice(prices.get(counter));
    }

    public void updateShareRates(){
        for (int i=0; i< pricesOfShares.size(); i++){
            ArrayList<Long> price = pricesOfShares.get(i);
            shareManagement.getShareByNumber(i).setPrice(price.get(counter));
        }
    }

    public void startUpdate(){
        timer.addTask(new TimerTask() {
            public void run() {
            updateShareRates();
            if (counter == prices.size()-1)
                counter = 0;
            else
                counter ++;
            }
        }, 10000, 10000);   //TODO refresh all 10 sec
    }
}
