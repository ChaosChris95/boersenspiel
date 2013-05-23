package boersenspiel.provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimerTask;

import boersenspiel.gui.UpdateTimer;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;

/**
 * User: Jan
 * Date: 17.05.13
 * Time: 13:31
 */

public class RealisticStockPriceProvider {

    private ShareManagement shareManagement;
    private final String stockName;
    //private final FileInputStream input;  //final
    public UpdateTimer timer = UpdateTimer.getInstance();

    public RealisticStockPriceProvider(String fileName){     //wtf?  , FileInputStream input
        shareManagement = ShareManagement.getInstance();
        stockName = fileName;
        try{
            final FileInputStream input = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {}
    }

    public void decodeFile(FileInputStream input, String stockName) throws IOException {
        String line;
        while((line = input.toString()) != null) {
            String[] splittedText = line.split("\n");
            //Wiederhole
            for (int i=0; i < splittedText.length; i++){
                if (i==1){
                    continue;
                }
                String[] splittedLine = line.split(",");
                try{
                    shareManagement.addShare(stockName, Long.parseLong(splittedLine[5]));
                } catch (ShareNameAlreadyExistsException e) {}
            }
        }
    }

    /*public void startUpdate(FileInputStream input){
        timer.addTask(new TimerTask() {
            public void run() {
                decodeFile(input, stockName);
            }
        }, 1000, 1000);
    }*/

}
