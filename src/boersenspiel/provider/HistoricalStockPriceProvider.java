package boersenspiel.provider;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.logging.Level;
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
    private int counter = 1;
    private ArrayList<Long> prices;
    private ArrayList<ArrayList<Long>> pricesOfShares;
    //private HashMap allShareFiles;
    //private String name;

    private final String PATH = "./Aktien/";

    public HistoricalStockPriceProvider() throws IOException {
        shareManagement = ShareManagement.getInstance();
        readShareRates();
    }

    public ArrayList<Long> readShareRate(Share share) throws IOException {
        BufferedReader reader = null;
        try {
            File fileURL = new File(ClassLoader.getSystemResource(PATH + share.getName() + ".csv").toURI());
            //name = share.getName(); //
            reader = new BufferedReader(new FileReader(fileURL));
            String line;
            prices = new ArrayList<Long>();
            boolean firstLine = true;
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
        }catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "FileNotFoundException", e);
        }catch (Exception e) {
            logger.log( Level.SEVERE, "Exception: ", e );
        }
        return prices;
    }

    public void readShareRates() throws IOException{
        pricesOfShares = new ArrayList<ArrayList<Long>>();
        //allShareFiles = new HashMap<String, ArrayList<Long>>();
        for (int i=0; i<shareManagement.getShareLength(); i++){
            pricesOfShares.add(readShareRate(shareManagement.getShareByNumber(i)));
            //allShareFiles.put(name,readShareRate(shareManagement.getShareByNumber(i)));
        }
    }

    public void updateShareRate(Share share){
        share.setPrice(prices.get(counter));
    }

    public void updateShareRates(){
        int size = pricesOfShares.size();
        for (int i=0; i< size; i++){
            ArrayList<Long> price = pricesOfShares.get(i);
            shareManagement.getShareByNumber(i).setPrice(price.get(counter));
        }
    }

    public void startUpdate(){
        logger.info("starting Timer");
        timer.addTask(new TimerTask() {
            public void run() {
            updateShareRates();
            if (counter == prices.size()-1)
                counter = 0;
            else
                counter ++;
            }
        }, 0, 4000);   //TODO refresh all 10 sec
    }
}
