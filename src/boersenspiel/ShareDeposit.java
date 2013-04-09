package boersenspiel;

/**
 * @author jan
 * Hier Lagern die ShareItems (Aktienpakete)
 */

public class ShareDeposit extends Asset{

	ShareItem[] shareItemList = new ShareItem[0];
	
	public ShareDeposit(){
		super("ShareDeposit");                  //TODO evtl DepositNumber?
	}
	
	public void addShareItem(ShareItem add){
		
		for (int i = 0; i<shareItemList.length; i++){
			if (shareItemList[i].getName() == add.getName()){
				shareItemList[i].addValue(add.getValue());
				shareItemList[i].addShareAmount(add.getShareAmount());
			}
		}
			
		ShareItem[] temporal = new ShareItem[shareItemList.length + 1];
		for (int i=0; i<shareItemList.length; i++){
			temporal[i] = shareItemList[i];
		}
		temporal[temporal.length-1] = add;
		shareItemList = temporal;
	}

    public void addShare(Share share) {
        for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName().equals(share.getName())) {
                shareItemList[i].addShare(share);
                return;
            }
        }
        addShareItem(new ShareItem(share.getName(),share));
    }
	
	public void removeShareItem(ShareItem remove){
		
		ShareItem[] temporal = new ShareItem[shareItemList.length - 1];
		boolean identified = false;
		for (int i=0; i<shareItemList.length-1; i++){
			if (shareItemList[i] == remove)
				identified = true;
				temporal[i] = shareItemList[i+1];
			if(identified){
					temporal[i] = shareItemList[i+1];
			}
			else
				temporal[i] = shareItemList[i];
		}
		shareItemList = temporal;
	}
	
	public long getValue(){
		long totalValue = 0;
		for (int i=0; i<shareItemList.length; i++){
			totalValue += shareItemList[i].getValue();
		}
		return totalValue;
	}
	
	public String print(){
		
		String output = "";
		for (int i=0; i<shareItemList.length; i++){
			output += (shareItemList[i].name) + "\n";
		}
		return output;
	}
	
	public String toString(){
		return ("ShareDeposit " + name + " mit einem Gesamtwert von " + getValue()
				+ ", dass aus folgenden ShareItems besteht:\n"
				+ print());
	}
	
	//Methoden kaufen=add verkaufen=remove
	
}
