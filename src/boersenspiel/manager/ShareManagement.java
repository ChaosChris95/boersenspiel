package boersenspiel.manager;

import boersenspiel.exceptions.SharenameAlreadyExistsException;
import boersenspiel.stock.Share;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class ShareManagement {
    private Share[] shares;

    public ShareManagement() {
        shares = new Share[0];
    }

    public void addShare(String name, long price) throws SharenameAlreadyExistsException { //TODO shares names have to be unique
        Share[] buffer = new Share[shares.length + 1];
        try {
            for (int i = 0; i < shares.length; i++) {
                buffer[i] = shares[i];
                if(shares[i].getName().equals(name)){
                    throw new SharenameAlreadyExistsException();
                }
            }
            buffer[buffer.length - 1] = new Share(name, price);
            shares = buffer;
        }catch (SharenameAlreadyExistsException e){
            e.printStackTrace();
        }


    }

    public Share[] getShareList(){
        return shares;
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
        String erg = "<br>";
        for (int i = 0; i < shares.length; i++) {
                erg += " " + shares[i].getName() + " " + shares[i].getPrice() + "<br>";
        }
        return erg;
    }

    public String listAll() {

        String display = "Alle verfügbaren Aktien:\n";
        for (int i = 0; i < shares.length; i++) {
            display += shares[i].getName() + "\n";
        }
        return display;
    }

}
