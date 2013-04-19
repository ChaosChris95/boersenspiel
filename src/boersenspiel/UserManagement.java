package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:57
 */

public class UserManagement {
    private Player[] players;

    public UserManagement() {
        players = new Player[0];
    }

    public void addPlayer(String name, long cash) {
        Player[] buffer = new Player[this.players.length + 1];
        for (int i = 0; i<this.players.length; i++){
            buffer[i] = players[i];
        }
        buffer[buffer.length - 1] = new Player(name);
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
