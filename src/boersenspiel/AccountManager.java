package boersenspiel;

/**
 * @author jan
 * Interface wird implementiert duch BancImpl
 */

public interface AccountManager {

	//Berechnung des Zinses
	long payInterest(long interest);
	
	//Liste ? auf
	void list();
	
	//Gibt den Gesamtwert des Bankaccounts aus
	long getValue();
	
	//Fuegt Geld hinzu
	void addValue(long add);
	
	//Nimmt Geld weg
	public void offValue(long off);
	
}
