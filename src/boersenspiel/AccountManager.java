package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */

public interface AccountManager {

    //Anlegen eines Mitspielers mit Name
    public void createPlayer(String name, long cash);

    //Aktie kaufen
    public void buy(String name, String shareName, int amount) throws Exception;

    //Aktie verkaufen
    public void sell(String name, String shareName, int amount) throws Exception;

    //Abfragen des Wertes im CashAccount
    public long getCashAccountValue(String name);

    //Abfragen des Wertes in ShareItem
    public long getShareItemValue(String playerName, String shareItemName);

    //Abfragen des Wertes in ShareDeposit
    public long getShareDepositValue(String name);

    //Abfrage des Gesamtwertes
    public long getAssetValue(String name);

	//Abfrage des Aktienkurses
    public long getRate(String shareName);
	
	//Aktienliste mit Kurswerten ausgeben
	public String getList();  //TODO muss noch implementiert werden

}
