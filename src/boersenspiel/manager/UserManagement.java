package boersenspiel.manager;

import boersenspiel.account.Player;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerAlreadyExistsException;
import boersenspiel.exceptions.PlayerDoesNotExistException;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class UserManagement {

    private static Logger logger = Logger.getLogger("UserManagement");
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    private static UserManagement instance = null;

    /**
     * Method to make certain there is only one instance of UserManagement (Singleton)
     * @return if it exists no instance a new one will be created, else the existing is used
     */

    public static UserManagement getInstance() {
        if(UserManagement.instance == null) {
            UserManagement.instance = new UserManagement();
        }
        return UserManagement.instance;
    }

    private Map<String, Player> players = new HashMap<String, Player>();

    private UserManagement() {
    }

    public void addPlayer(String name, long cash) throws PlayerAlreadyExistsException, NegativeValueException {

        if (players.containsKey(name)) {
            throw new PlayerAlreadyExistsException(rs.getString("UserExist"));
        }
        Player newPlayer = new Player(name);
        logger.fine(rs.getString("UserCreate"));
        newPlayer.addCash(cash);
        players.put(name, newPlayer);
    }

    public Player getPlayer(String name) throws PlayerDoesNotExistException {
        if (players.containsKey(name)) {
            logger.fine(rs.getString("User1"));
            return players.get(name);
        }
        throw new PlayerDoesNotExistException(rs.getString("UserNo"));

    }




}
