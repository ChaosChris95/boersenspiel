package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class ShareManagement {
    private Share[] shares;

    public ShareManagement(){
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

    public long getSpecificRate(String name) {
        for (int i = 0; i < shares.length; i++){
            if (name.equals(shares[i].getName())) {
                return shares[i].getPrice();
            }
        }
        return 0;   //TODO vllt Exception
    }

    public void listAll(){
        for (int i = 0; i < shares.length; i++){
            shares[i].toString();
        }
    }

}
