package boersenspiel;

/*
 * Vermoegenswerte des Spielers
 * davon Abgeleitet sind CashAccount, ShareItems und ShareDeposit
 */

public abstract class Asset {	//Kapital
	
	final String name;
	
	public Asset(String name){
		this.name = name;
	}
   
  public String getName(){
    return name;
  }
	
	public abstract long getValue();
	
	@Override
	public abstract String toString();
		
	public boolean equals(Object obj){
		if (obj instanceof Asset){
			Asset a =(Asset) obj;
			if (this.name == a.name){
				return true;
			}
		}
		return false;

	}
	
}
