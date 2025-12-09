import java.io.Serializable;
import java.util.HashMap;

/**
 * This class stores players in a HashMap using their names as keys.
 * If a requested player does not exist, a new Player is created
 * automatically and added to the list.
 *
 * This class is Serializable so that the player list can be saved
 * and loaded between game sessions.
 */
public class PlayerList implements Serializable {
	// private static final long serialVersionUID = 1L;

	public HashMap<String, Player> thisPlayerList;
	

    /**
     * creates an empty PlayerList with no players added yet.
     */
	public PlayerList() {
		thisPlayerList = new HashMap<>();
	}
	
	 /**
      * get a Player object by name. If the player does not already exist,
      * a new Player is created, putted into the list, and then returned.
      *
      *
      * @param playerName: The player's name
      * @return the Player object associated with this name
      */
	public Player getPlayer(String playerName) {
		if (!thisPlayerList.containsKey(playerName.strip())) {
			Player thisPlayer = new Player(playerName.strip());
			thisPlayerList.put(playerName.strip(), thisPlayer);
		}

		Player thisPlayer = thisPlayerList.get(playerName.strip());

		return thisPlayer;
	}

}
