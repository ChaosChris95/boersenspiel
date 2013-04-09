package boersenspiel;

/**
 * @author jan
 * Der Bankaccount des Spielers
 */

public class CashAccount extends Asset{ //Konto

	private long accountValue; //Kontostand
	
	public CashAccount(long accountValue){
		super("CashAccount");                       //TODO evtl Kontonummer?
		this.accountValue=accountValue;	//Startstand
	}
	
	public long getValue(){
		return accountValue;
	}

    public void addCash(long cash){
        accountValue += cash;
    }
	
	public String toString(){
		return ("Der Kontostand des Accounts " + name + " betraegt: " + getValue());
	}
	
	public void buy(long price){
		
		if (price > accountValue){
			System.out.println("Zu Teuer");
			return;
		}
		accountValue -= price;
	}
	
	public void sell(long price){
		accountValue += price;
	}
	
}
