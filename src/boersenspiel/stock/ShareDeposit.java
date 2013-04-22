package boersenspiel.stock;

import boersenspiel.account.Asset;

/**
 * Created with IntelliJ IDEA.
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class ShareDeposit extends Asset {

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

    public String getName(){
        return name;
    }

    public void addShare(Share share, int amount) {
        for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName().equals(share.getName())) {
                shareItemList[i].addShareAmount(amount);
                return;
            }
        }
        addShareItem(new ShareItem(share, amount));
    }

    public long sellShare(Share share, int amount) {
        for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemList[i].getName().equals(share.getName())) {
                shareItemList[i].removeShareAmount(amount);
                return shareItemList[i].getValue() * amount;
            }

        }
        return 0;
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

    public long getShareItemValue(String shareItemName){
        for (int i = 0; i < shareItemList.length; i++){
            if (shareItemName.equals(shareItemList[i].getName())){
               return shareItemList[i].getValue();
            }
        }
        return 0;
    }

    public int getShareAmount(String shareItemName) {
        for (int i = 0; i < shareItemList.length; i++) {
            if (shareItemName.equals(shareItemList[i].getName())) {
                return shareItemList[i].getShareAmount();
            }
        }
        return 0;
    }
	
	public String print(){
		
		String output = "";
		for (int i=0; i<shareItemList.length; i++){
			output += (shareItemList[i].getName()) + "\n";
		}
		return output;
	}
	
	public String toString(){
		return ("ShareDeposit " + name + " mit einem Gesamtwert von " + getValue()
				+ ", dass aus folgenden ShareItems besteht:\n"
				+ print());
	}
	

	
}
