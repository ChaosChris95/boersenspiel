package boersenspiel;

/*
 * Lässt dass Programm laufen
 */

public class TestRunner {
	
	public static void main(String[] args) throws Exception {

        Player one = new Player("Bianca");
        one.addCash(40000);
        System.out.println(one.toString());

        ShareManagement shareManagement = new ShareManagement();
        shareManagement.addShare("BMW", 100);
        shareManagement.addShare("Audi", 200);

        System.out.println(one.getCashAccountValue());
        one.addShareToDeposit(shareManagement.getShare("BMW"), 15);
        System.out.println(one.getCashAccountValue());
        System.out.println(one.getShareDepositValue());
        System.out.println(one.getStockList());
        one.subShareFromDeposit(shareManagement.getShare("BMW"), 5);
        System.out.println(one.getCashAccountValue());
        System.out.println(one.getShareAmount("BMW"));

        AccountManager accountManager = new AccountManagerImpl(shareManagement);
        System.out.println(accountManager.getList());
	
		/*CashAccount cashAccount = new CashAccount ("Sparkasse", 10000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(4000);
		cashAccount.addCash(6000);
		System.out.println(cashAccount.toString());
		cashAccount.buy(14000);
		System.out.println(cashAccount.toString());*/
	
	}
}
