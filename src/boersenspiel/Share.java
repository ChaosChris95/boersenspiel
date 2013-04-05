package boersenspiel;

/*
 * 
 */

public class Share {

	private final String name;
	private long price;	//Kurs
	
	public Share(String name, long price){
		this.name=name;
		this.price=price;
	}
	
	public String getName(){
		return name;
	}
	
	public long getPrice(){
		return price;
	}
	
	public String toString(){
		return ("Share mit den Namen " + name + " und den Preis " + price);
	}
	
}
