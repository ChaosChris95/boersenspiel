package boersenspiel;

/**
 * @author jan
 * Aktienpaktet
 */

public class ShareItem extends Asset{
	
	private int shareAmount;
	private long value;
	

	public ShareItem(String name){
		super(name);
		shareAmount = 0;
		value = 0;		
	}
	
	public int getShareAmount(){
		return shareAmount;
	}
	
	/*public void setShareAmount(int set){
		shareAmount = set;
	}*/
	
	public void addShareAmount(int add){
		shareAmount += add;
	}

	public void addShare(Share add){	
		if (add.getName() == name){
			shareAmount++;
			value += add.getPrice();
		}
		else if (add.getName() != name){
			System.out.println("Falsches Share wird zum ShareItem hinzugefügt");
		}	
	}
	
	public long getValue(){
		return value;
	}
	
	/*public void setValue(long set){
		value = set;
	}*/
	
	public void addValue(long add){
		value += add;
	}

	public String toString(){
		return ("ShareItem " + name + " mit einem Gesamtwert von " + getValue()); 		
	}
	
}
