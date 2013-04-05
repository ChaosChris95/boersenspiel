package boersenspiel;

/*
 * 
 */

public class TestRunner {
	
	public static void main(String[] args){
				
		/*Player player = new Player("Franz Franke", cashAccount);
		System.out.println(player.toString());
		System.out.println(player.isBroken());*/
		
		Share share = new Share("Siemenswerk", 100); 
		System.out.println(share.toString());
		
		ShareItem shareItem = new ShareItem ("Siemens");
		shareItem.addShare(share);
		System.out.println(shareItem.toString());
		
		ShareDeposit shareDeposit = new ShareDeposit("Projekt 1");
		shareDeposit.addShareItem(shareItem);	
		System.out.println(shareDeposit.toString());
		
		shareDeposit.removeShareItem(shareItem);
		System.out.println(shareDeposit.toString());
	
		CashAccount cashAccount = new CashAccount ("Sparkasse", 10000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(4000);
		cashAccount.sell(6000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(14000);
		System.out.println(cashAccount.toString());
	
	}
}
