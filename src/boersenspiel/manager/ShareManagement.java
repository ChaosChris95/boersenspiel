package boersenspiel.manager;

import boersenspiel.exceptions.ShareDoesNotExistException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.stock.Share;

import java.util.*;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class ShareManagement {

    ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    private static Logger logger = Logger.getLogger("ShareManagement");

    private static ShareManagement instance = null;

    /**
     * Method to make certain there is only one instance of ShareManagement (Singleton)
     * @return if it exists no instance a new one will be created, else the existing is used
    */

    public static ShareManagement getInstance() {
        if(ShareManagement.instance == null) {
            ShareManagement.instance = new ShareManagement();
        }
        return ShareManagement.instance;
    }

    private List<Share> shareList = new ArrayList<Share>();


    private ShareManagement() {
    }

    /**
     * will create a new share in system
     * @param name String for share name
     * @param price Long for start price
     * @exception ShareNameAlreadyExistsException if share already exists
     */

    public void addShare(String name, Long price) throws ShareNameAlreadyExistsException {
       for (Share share : shareList) {
           if (share.getName().equals(name)) {
               throw new ShareNameAlreadyExistsException(rs.getString("ShareExist"));
           }
       }
        shareList.add(new Share(name,price));
        logger.info(rs.getString("Share1")+ " " + name + " " + rs.getString("SharePrice") + " " + price + " " + rs.getString("ShareCreate"));
    }

    /**
     * will delete a share from system
     * @param name String for share name
     * @exception ShareDoesNotExistException if share does not exist in system
     */

    public void deleteShare(String name) throws ShareDoesNotExistException {
       for (Share share : shareList) {
           if (share.getName().equals(name)) {
              shareList.remove(name);
              logger.info(rs.getString("Share1")+ " " + name + " " + rs.getString("ShareDelete"));
           }
       }
        throw new ShareDoesNotExistException(rs.getString("ShareNo"));
    }

    /**
     * get the size from share list
     * @return Integer with size of shareList
     */

    public int getShareLength(){
        return shareList.size();
    }

    /**
     * will check if given share is in the System
     * @param shareName String for share name
     * @return Share if it exists, else return NULL
     */

    public Share getShare(String shareName) {
        for (Share share : shareList) {
            if (share.getName().equals(shareName)){
               return share;
            }
        }
        return null;
    }

    /**
     * find share by given number
     * @param number Integer
     * @return Share
     */

    public Share getShareByNumber (Integer number) {
        return shareList.get(number);
    }

    /**
     * will return price for a given share
     * @param name String for share name
     * @return long price of share
     */

    public long getSpecificRate(String name) {
        for (Share share : shareList) {
            if (share.getName().equals(name)) {
                return share.getPrice();
            }
        }
        return 0;
    }


    /**
     * String representation is "share.getName=SHARENAME,share.getPrice=SHAREPRICE"
     * Where SHARENAME is the share name and where SHAREPRICE is the price of the share
     * print all existing shares of the system in a StringBuilder sorted
     * @return formatted String with shares and there price
     */

    public String getSharesAndRates(){
        StringBuilder erg = new StringBuilder();
        erg.append( "<br>" );
        Collections.sort(shareList);
        for (Share share : shareList) {
            erg.append(' ');
            erg.append( share.getName( ) );
            erg.append( ' ' );
            erg.append( share.getPrice() );
            if(Locale.getDefault() == Locale.GERMAN) {
                erg.append(" " + Currency.getInstance("EUR").getSymbol());
            } else {
                erg.append(" " + Currency.getInstance("GBP").getSymbol());
            }
            erg.append( "<br>" );

        }
        return erg.toString();
    }

    /*public String listAll() {

        StringBuilder display = new StringBuilder();
        display.append( rs.getString("ShareAll")+ " :\n" );
        Collections.sort(shareList);
        for (Share share : shareList) {
            display.append(share.getName());
            display.append('\n');
        }
        return display.toString();
    } */

}
