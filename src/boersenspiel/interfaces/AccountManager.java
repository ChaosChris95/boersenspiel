package boersenspiel.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public interface AccountManager {

    /**
     * Creates a player with a name
     * @param name
     * @param cash
     */

    void createPlayer(String name, long cash);


    /**
     * buy a share
     * @param name
     * @param shareName
     * @param amount
     * @throws Exception
     */

    void buy(String name, String shareName, int amount) throws Exception;

    /**
     * sell a share
     * @param name
     * @param shareName
     * @param amount
     * @throws Exception
     */

    void sell(String name, String shareName, int amount) throws Exception;

    /**
     * get the amount of money a certain player has
     * @param name
     */
    long getCashAccountValue(String name);

    /**
     * get the total Value of the ShareDeposit
     * @param name
     * @return long
     */

    long getShareDepositValue(String name);

    /**
     * get the value of an asset
     * @param name
     * @return long
     */

    long getAssetValue(String name);

    /**
     * get the current price of a Share
     * @param shareName
     * @return long
     */
}
