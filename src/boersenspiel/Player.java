package boersenspiel;

/*
 * 
 */

public class Player {
		
	private final String name;	//Name
	private boolean broken =false;	//pleite
	private CashAccount cashAccount;
	
	public Player(String name, CashAccount cashAccount){ //
		this.name=name;
		broken=false;	//Startwert aus Account beachten
		this.cashAccount = cashAccount;	//
	}
	
	public long getProperty(){	//
		return cashAccount.getValue();
	}
	
	public String getName(){	//
		return name;
	}
	
	public boolean isBroken(){
		if (getProperty() <= 0){
			broken = true;
		}
		return broken;
	}
	
	public String toString(){
		return "Spieler mit dem Namen " + name + " und mit dem Kontostand " + getProperty();
	}

}
