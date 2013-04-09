package boersenspiel;

/**
 * @author jan
 * Interface wird implementiert duch BancImpl
 */

public interface AccountManager {

    //Anlegen eines Mitspielers mit Name
    public void createPlayer(String name, long cash);

    //Aktie kaufen
    public void buy(String name, String shareName, int value);

    //Aktie verkaufen
    public void sell(String name, String shareName, int value);

    //Abfragen des Wertes im CashAccount
    public long getCashAccountValue(String name);

    //Abfragen des Wertes in ShareItem
    public long getShareItemValue(String name);

    //Abfragen des Wertes in ShareDeposit
    public long getShareDepositValue(String name);

    //Abfrage des Gesamtwertes
    public long getAssetValue(String name);

	//Abfrage des Aktienkurses
    public long getRate(String shareName);
	
	//Aktienliste mit Kurswerten ausgeben
	public void getList();  //TODO muss noch implementiert werden

}
