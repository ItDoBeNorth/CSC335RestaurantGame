import java.util.HashMap;

public class PlayerList {

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
