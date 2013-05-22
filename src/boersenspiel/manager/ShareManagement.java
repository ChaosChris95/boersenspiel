package boersenspiel.manager;

import boersenspiel.exceptions.ShareDoesNotExistException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.stock.Share;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

    private List<Share> shareList = new ArrayList<Share>();

    private ShareManagement() {
    }

    public void addShare(String name, Long price) throws ShareNameAlreadyExistsException {
       for (Share share : shareList) {
           if (share.getName().equals(name)) {
               throw new ShareNameAlreadyExistsException("Aktie existiert schon");
           }
       }
        shareList.add(new Share(name,price));
        logger.fine("Aktie " + name + " mit einem Preis von " + price + " erstellt.");
    }


    public void deleteShare(String name) throws ShareDoesNotExistException {
       for (Share share : shareList) {
           if (share.getName().equals(name)) {
              shareList.remove(name);
              logger.fine("Aktie " + name + " wurde aus dem System gelöscht.");
           }
       }
        throw new ShareDoesNotExistException("Aktiene existiert nicht");
    }

    public int getShareLength(){
            return shareList.size();
    }


    public Share getShare(String shareName) {
        for (Share share : shareList) {
            if (share.getName().equals(shareName)){
               return share;
            }
        }
        return null;
    }

    public Share getShareByNumber (Integer number) {
        return shareList.get(number);
    }

    public long getSpecificRate(String name) {
        for (Share share : shareList) {
            if (share.getName().equals(name)) {
                return share.getPrice();
            }
        }
        return 0;
    }


    public String getSharesAndRates(){
        StringBuilder erg = new StringBuilder();
        erg.append( "<br>" );
        Collections.sort(shareList);
        for (Share share : shareList) {
            erg.append(' ');
            erg.append( share.getName( ) );
            erg.append( ' ' );
            erg.append( share.getPrice() );
            erg.append( "<br>" );

        }
        return erg.toString();
    }

    public String listAll() {

        StringBuilder display = new StringBuilder();
        display.append( "Alle verfügbaren Aktien:\n" );
        Collections.sort(shareList);
        for (Share share : shareList) {
            display.append(share.getName());
            display.append('\n');
        }
        return display.toString();
    }

}
