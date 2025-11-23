import java.util.HashMap;

public class PlayerList {

	public static HashMap<String, Player> thisPlayerList = new HashMap<>();
	
	public Player getPlayer(String playerName) {
		
		Player thisPlayer = thisPlayerList.get(playerName.strip());
		
		if (thisPlayer == null) {
			thisPlayer = new Player(playerName.strip());
			thisPlayerList.put(playerName.strip(), thisPlayer);
		}
		
		return thisPlayer;
	}
	
	
}
