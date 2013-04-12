package boersenspiel;

/*
 * Lässt dass Programm laufen
 */

public class TestRunner {
	
	public static void main(String[] args){

        Player one = new Player("Bianca");
        one.addCash(40000);
        System.out.println(one.toString());

		Share share1 = new Share("Siemens", 100); 
		System.out.println(share1.toString());
		Share share2 = new Share("Ford", 200);
		System.out.println(share2.toString());
		Share share3 = new Share("BMW", 150); 
		System.out.println(share3.toString());

		
		ShareItem shareItem = new ShareItem (share1, 0);
		shareItem.addShareAmount(40);
		System.out.println(shareItem.toString());
		
		ShareItem shareItem2 = new ShareItem (share2, 0);
		shareItem2.addShareAmount(80);
		System.out.println(shareItem2.toString());
		
		ShareDeposit shareDeposit = new ShareDeposit();
		shareDeposit.addShareItem(shareItem);
		shareDeposit.addShareItem(shareItem2);
		System.out.println(shareDeposit.toString());
		shareDeposit.removeShareItem(shareItem);
		System.out.println(shareDeposit.toString());

        shareDeposit.sellShare(share2, 20);
        shareDeposit.sellShare(share3, 150);
        System.out.println(shareDeposit.toString());
	
		/*CashAccount cashAccount = new CashAccount ("Sparkasse", 10000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(4000);
		cashAccount.addCash(6000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(14000);
		System.out.println(cashAccount.toString());*/
	
	}
}
