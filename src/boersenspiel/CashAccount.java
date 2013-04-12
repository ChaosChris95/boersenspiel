package boersenspiel;

/**
 * @author jan
 * Der Bankaccount des Spielers
 */

public class CashAccount extends Asset{ //Konto

	private long accountValue; //Kontostand
	
	public CashAccount(long accountValue){
		super("CashAccount");
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
	
	public void subCash(long cash) throws Exception{
		
		if (cash > accountValue){
            throw new Exception("Sie besitzen nicht genügend Geld!");
		}
		accountValue -= cash;
	}
	
}
