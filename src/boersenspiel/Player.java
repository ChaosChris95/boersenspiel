package boersenspiel;

/**
 * @author jan
 *         Der Spieler des Boersenspiels
 */

public class Player {

    private final String name;    //Name
    private boolean broken = false;    //pleite
    private CashAccount cashAccount;
    private ShareDeposit shareDeposit;

    public Player(String name) {
        this.name = name;
        broken = false;    //!Startwert aus Account beachten
        cashAccount = new CashAccount(0);

    }

    public void addCash(long cash) {
        cashAccount.addCash(cash);
    }

    public long getCashAccountValue() {
        return cashAccount.getValue();
    }

    public long getProperty() {
        return cashAccount.getValue();
    }

    public String getName() {    //
        return name;
    }

    public boolean isBroken() {
        if (getProperty() <= 0) {
            broken = true;
        }
        return broken;
    }

    public String toString() {
        return "Spieler mit dem Namen " + name + " und mit dem Kontostand " + getProperty();
    }

}
