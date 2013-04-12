package boersenspiel;

/**
 * @author jan
 * Aktienpaktet
 */

public class ShareItem extends Asset{
	
	private int shareAmount;
	private long value;
    private final Share share;
	

	public ShareItem(Share share, int shareAmount){
		super(share.getName());
		this.shareAmount = shareAmount;
		value = share.getPrice()*shareAmount;
        this.share = share;
	}
	
	public int getShareAmount(){
		return shareAmount;
	}
	
	public void addShareAmount(int amount){
        value += this.share.getPrice()*amount;
        shareAmount += amount;
	}

    public void removeShareAmount(int amount) {
       if (amount > shareAmount) {
          throw new Error("Nicht genug Aktien vorhanden!");
       } else {
          shareAmount -= amount;
       }

    }
	
	public long getValue(){
		return value;
	}
	
	public void setValue(long set){
		value = set;
	}
	
	public void addValue(long add){
		value += add;
	}

	public String toString(){
		return ("ShareItem " + name + " mit einem Gesamtwert von " + getValue()); 		
	}
	
}
