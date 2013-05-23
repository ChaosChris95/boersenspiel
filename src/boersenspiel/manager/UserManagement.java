package boersenspiel.manager;

import boersenspiel.account.Player;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerAlreadyExistsException;
import boersenspiel.exceptions.PlayerDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class UserManagement {

    private static UserManagement instance = null;
    public static UserManagement getInstance() {
        if(UserManagement.instance == null) {
            UserManagement.instance = new UserManagement();
        }
        return UserManagement.instance;
    }

    //private Player[] players;
    private Map<String, Player> players = new HashMap<String, Player>();

    private UserManagement() {
        //this.players = new Player[0];
    }

    public void addPlayer(String name, long cash) throws PlayerAlreadyExistsException, NegativeValueException {

        if (players.containsKey(name)) {
            throw new PlayerAlreadyExistsException("Ein Spieler mit diesem Namen existiert schon");
        }
        Player newPlayer = new Player(name);
        newPlayer.addCash(cash);
        players.put(name, newPlayer);

    }



    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        if (players.containsKey(name)) {
            return players.get(name);
        }
        throw new PlayerDoesNotExistException("Spieler existiert nicht");

    }




}
