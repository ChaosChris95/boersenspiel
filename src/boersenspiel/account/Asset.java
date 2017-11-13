package boersenspiel.account;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */
werwerwerwer
public abstract class Asset {
	
	protected String name;

    /**
     * Constructor Asset
     * @param name  set variable name to given name
     */
	
	public Asset(String name){
		this.name = name;
	}

   
	public String getName(){
		return name;
	}

	public abstract long getValue();

	public abstract String toString();

    /**
     * check if given Object equals an Asset
     * @param obj Object unknown type
     * @return true if given Object is from type Asset and name equals another Asset name, else returns false
     */
		
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
