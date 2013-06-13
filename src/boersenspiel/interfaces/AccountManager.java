package boersenspiel.interfaces;

import boersenspiel.exceptions.NegativeValueException;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public interface AccountManager {

    /**
     * Creates a player with a name and given cash
     * @param name String for player name
     * @param cash Long for cash value
     * @exception NegativeValueException if the given Long is a negative number
     */

    void createPlayer(String name, Long cash) throws NegativeValueException;


    /**
     * buy a share for given Player with given amount
     * @param name  String for player name
     * @param shareName String for share name
     * @param amount Integer for amount of share
     * @throws Exception
     */

    void buy(String name, String shareName, Integer amount) throws Exception;

    /**
     * sell a share for given Player with given amount
     * @param name  String for player name
     * @param shareName String for share name
     * @param amount Integer for amount of share
     * @throws Exception
     */

    void sell(String name, String shareName, Integer amount) throws Exception;

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
     * get the current lost of Shares from Player
     * @param name
     * @return String
     */

    String getStock(String name);

    /**
     * make player to bot
     * @param name
     */

    void botPlayer(String name);


}
