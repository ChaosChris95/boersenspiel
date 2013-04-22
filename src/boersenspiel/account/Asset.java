package boersenspiel.account;

/**
 * Created with IntelliJ IDEA.
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public abstract class Asset {
	
	protected String name;
	
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
			if (this.name.equals(a.name)){
				return true;
			}
		}
		return false;
	}	
}
