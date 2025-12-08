import java.io.Serializable;
import java.util.HashMap;

public class PlayerList implements Serializable {
	// private static final long serialVersionUID = 1L;

	public HashMap<String, Player> thisPlayerList;

	public PlayerList() {
		thisPlayerList = new HashMap<>();
	}

	public Player getPlayer(String playerName) {
		if (!thisPlayerList.containsKey(playerName.strip())) {
			Player thisPlayer = new Player(playerName.strip());
			thisPlayerList.put(playerName.strip(), thisPlayer);
		}

		Player thisPlayer = thisPlayerList.get(playerName.strip());

		return thisPlayer;
	}

}
