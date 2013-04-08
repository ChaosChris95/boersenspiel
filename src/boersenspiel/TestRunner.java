package boersenspiel;

/*
 * 
 */

public class TestRunner {
	
	public static void main(String[] args){
				
		Share share1 = new Share("Siemens", 100); 
		System.out.println(share1.toString());
		Share share2 = new Share("Siemens", 200); 
		System.out.println(share2.toString());
		Share share3 = new Share("BMW", 100); 
		System.out.println(share3.toString());
		
		ShareItem shareItem = new ShareItem ("Siemens");
		shareItem.addShare(share1);
		shareItem.addShare(share2);
		System.out.println(shareItem.toString());
		
		ShareItem shareItem2 = new ShareItem ("BMW");
		shareItem2.addShare(share3);
		System.out.println(shareItem2.toString());
		
		ShareDeposit shareDeposit = new ShareDeposit("Projekt 1");
		shareDeposit.addShareItem(shareItem);
		shareDeposit.addShareItem(shareItem2);
		System.out.println(shareDeposit.toString());
		shareDeposit.removeShareItem(shareItem);
		System.out.println(shareDeposit.toString()); //remove what de fuck
	
		/*CashAccount cashAccount = new CashAccount ("Sparkasse", 10000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(4000);
		cashAccount.sell(6000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(14000);
		System.out.println(cashAccount.toString());*/
	
	}
}
