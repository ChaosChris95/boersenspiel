package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class CashAccount extends Asset{

	private long accountValue;
	
	public CashAccount(long accountValue){
		super("CashAccount");
		this.accountValue=accountValue;
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
	
	public void subCash(long cash) throws NotEnoughMoneyException {
		
		if (cash > accountValue){
            throw new NotEnoughMoneyException("Sie besitzen nicht genügend Geld!");
		}
		accountValue -= cash;
	}
	
}
