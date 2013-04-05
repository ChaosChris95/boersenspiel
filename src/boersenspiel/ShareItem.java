package boersenspiel;

/*
 * Aktienpakete
 */

public class ShareItem extends Asset{
	
	public int amount;	//Stueckzahl 
	private Share[] shareList = new Share[0];

	public ShareItem(String name){
		super(name);
	}
	
	public void addShare(Share add){
		Share[] temporal = new Share[shareList.length + 1];
		for (int i=0; i<shareList.length; i++){
			temporal[i] = shareList[i];
		}
		temporal[temporal.length-1] = add;
		shareList = temporal;
	}
	
	public long getValue(){
		long totalValue = 0;
		for (int i=0; i<shareList.length; i++){
			totalValue += shareList[i].getPrice();	
		}
		return totalValue;
	}

	public String print(){
		String output = "";
		for (int i=0; i<shareList.length; i++){
			output += (shareList[i].getName()) + "\n";
		}
		return output;
}

	public String toString(){
		return ("ShareItem " + name + " mit einem Gesamtwert von " + getValue() 
				+ ", dass aus folgenden Shares besteht:\n"
				+ print());
	}
	
}
