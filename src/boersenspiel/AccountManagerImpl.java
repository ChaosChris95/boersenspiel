package boersenspiel;

/**
 * @author jan
 * Implementiert das Interface BancCustomerView
 */

public class AccountManagerImpl {

	/*
	Ein neues Konto anlegen (Giro- oder Festgeldkonto).
	Einen Kontostand abfragen.
	Einen Betrag auf ein Konto einzahlen.
	Einen Betrag von einem Konto abheben.
	Von einem Konto auf ein anderes Konto (bei derselben Bank) eine Überweisung durchführen. 
	
	alles aus BancImpl auch ins Internet
	nicht verzinsen() und list()
	*/

	private CashAccount[] accountList = new CashAccount[10];
	private int accountNumber = 1000; //dummy?
	private long bancValue = 0;
	
	public AccountManagerImpl (CashAccount[] accountList, int accountNumber){
		this.accountList = accountList;
		this.accountNumber = accountNumber;
	}
	
	public long payInterest(long interest){
		bancValue = bancValue + bancValue * interest;
		return bancValue;
	}
	
	public void list(){	//?
	
	}
	
	/*public long getValue(){

	}*/
	
	public void addValue(long add){
		bancValue += add;
	}
	
	public void offValue(long off){
		bancValue -= off;
	}
	
	//Überweisung?
	
}