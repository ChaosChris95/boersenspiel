package boersenspiel.provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

import boersenspiel.gui.UpdateTimer;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.Share;

/**
 * User: Jan
 * Date: 17.05.13
 * Time: 13:31
 */

public class RealisticStockPriceProvider extends StockPriceProvider{  //TODO inherit from StockPriceProvider

    private ShareManagement shareManagement;
    public UpdateTimer timer = UpdateTimer.getInstance();
    URL helpURL;
    String fileName;
    String[] splitText;

    public RealisticStockPriceProvider(String fileName){
        shareManagement = ShareManagement.getInstance();
        helpURL = getClass().getResource("../../Aktien/" + fileName);
        if (helpURL == null){
            return; //NullPointerException
        }
        String text = helpURL.toString();
        this.fileName = fileName;
        splitText = text.split("\n");
        startUpdate();
    }
    public void updateShareRates(){

    }

    public void updateShareRate(Share share){

    }

    public void decodeFile(int counter, String[] splitText, String stockName) throws IOException {
        String[] splitLine = splitText[counter].split(",");   //NullPointerException
        if (counter==1){
            return;
        }
        if (counter==2){
            try{
                shareManagement.addShare(fileName, Long.parseLong(splitLine[4]));
            } catch (ShareNameAlreadyExistsException e){}

        }
        else {
            shareManagement.getShare(fileName).setPrice(Long.parseLong(splitLine[4]));
        }
    }

    public void startUpdate(){
        timer.addTask(new TimerTask() {
            public void run() {
                try{
                    int counter = 0;
                    decodeFile(counter, splitText, fileName);
                    counter ++;
                } catch (IOException e){}
            }
        }, 1000, 1000);
    }
}
