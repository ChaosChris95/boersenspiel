package boersenspiel.manager;

import boersenspiel.stock.Share;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */
public class ShareManagement {
    private Share[] shares;

    public ShareManagement() {
        shares = new Share[0];
    }

    public void addShare(String name, long price) {
        Share[] buffer = new Share[shares.length + 1];
        for (int i = 0; i < shares.length; i++) {
            buffer[i] = shares[i];
        }
        buffer[buffer.length - 1] = new Share(name, price);
        shares = buffer;
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

    public Share getShareNumber (int number) {
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
        return 0;                                             //TODO maybe Exception
    }

    public String listAll() {

        String display = "Alle verfügbaren Aktien:\n";
        for (int i = 0; i < shares.length; i++) {
            display += shares[i].getName() + "\n";
        }
        return display;
    }

}