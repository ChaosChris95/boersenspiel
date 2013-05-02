package boersenspiel.manager;

import boersenspiel.account.Player;

/**
 * Created with IntelliJ IDEA.
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

    private Player[] players;

    private UserManagement() {
        this.players = new Player[0];
    }

    public void addPlayer(String name, long cash) {
        Player[] buffer = new Player[this.players.length + 1];
        for (int i = 0; i<this.players.length; i++){
            buffer[i] = players[i];
        }
        Player p = new Player(name);
        p.addCash(cash);
        buffer[buffer.length - 1] = p;
        players = buffer;
    }

    public Player getPlayer(String name) {
        for (int i = 0; i<this.players.length; i++) {
            if (name.equals(players[i].getName())) {
                return players[i];
            }
        }
        return null;
    }




}
