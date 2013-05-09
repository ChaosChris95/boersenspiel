package boersenspiel.manager;

import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.stock.Share;

import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class ShareManagement {

    private static Logger logger = Logger.getLogger("ShareManagement");

    private static ShareManagement instance = null;
    public static ShareManagement getInstance() {
        if(ShareManagement.instance == null) {
            ShareManagement.instance = new ShareManagement();
        }
        return ShareManagement.instance;
    }

    private Share[] shares;

    private ShareManagement() {
        shares = new Share[0];
    }

    public void addShare(String name, long price) throws ShareNameAlreadyExistsException {
        Share[] buffer = new Share[shares.length + 1];
        try {
            for (int i = 0; i < shares.length; i++) {
                buffer[i] = shares[i];
                if(shares[i].getName().equals(name)){
                    throw new ShareNameAlreadyExistsException();
                }
            }
            buffer[buffer.length - 1] = new Share(name, price);
            shares = buffer;
            logger.fine("Aktie " + name + " mit einem Preis von " + price + " erstellt.");
        }catch (ShareNameAlreadyExistsException e){
            e.printStackTrace();
        }
    }

    public void deleteShare(String name) {
        Share[] buffer = new Share[shares.length - 1];
        int j = 0;
        for (int i = 0; i < shares.length; i++) {
            if (shares[i].getName().equals(name)){
            continue;
            }
            buffer[j] = shares[i];
            j++;
        }
        shares = buffer;
        logger.fine("Aktie " + name + " wurde aus dem System gelöscht.");
    }

    public Share[] cloneShareList(){
        Share[] sharesCopy = new Share[shares.length];
        for (int i=0; i<shares.length; i++){
            sharesCopy[i] = shares[i].clone();
        }
        return sharesCopy;
    }

    public int getShareLength(){
            return shares.length;
    }


    public Share getShare(String shareName) {
        for (int i = 0; i < shares.length; i++) {
            if (shareName.equals(shares[i].getName())) {
                return shares[i];
            }
        }
        return null;
    }

    public Share getShareByNumber (int number) {
        for (int i = 0; i < shares.length; i++) {
            if (number == i) {
                return shares[i];
            }
        }
        return null;
    }

    public long getSpecificRate(String name) {
        for (int i = 0; i < shares.length; i++) {
            if (name.equals(shares[i].getName())) {
                return shares[i].getPrice();
            }
        }
        return 0;
    }

    public String getSharesAndRates(){
        StringBuilder erg = new StringBuilder();
        erg.append( "<br>" );
        for (int i = 0; i < shares.length; i++) {
            erg.append( ' ' );
            erg.append( shares[i].getName( ) );
            erg.append( ' ' );
            erg.append( shares[i].getPrice() );
            erg.append( "<br>" );
        }
        return erg.toString();
    }

    public String listAll() {

        StringBuilder display = new StringBuilder();
        display.append( "Alle verfügbaren Aktien:\n" );
        for (int i = 0; i < shares.length; i++) {
            display.append(shares[i].getName() );
            display.append( '\n' );
        }
        return display.toString();
    }

}
