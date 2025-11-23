public class Player {
	private int day;
	private int score;
	private String name;

	/**
	 * Creates a new player with a given name starting at day 0 with score 0. Holds completed days.
	 * 
	 * @param name the name chosen for the player by the user.
	 */
	public Player(String name) {
		this.name = name;
		score = 0;
		day = 0;
	}

	/**
	 * Creates a player with a given name, day, and score.
	 * 
	 * @param name  the name chosen for the player by the user.
	 * @param day   the current day the player is on.
	 * @param score the current score of the player.
	 */
	public Player(String name, int day, int score) {
		this.name = name;
		this.day = day;
		this.score = score;
	}

	/**
	 * move the player to the next day
	 */
	public void nextDay() {
		day++;
	}

	/**
	 * adds the daily score to player's score
	 * 
	 * @param dailyScore the score earned for the current day
	 */
	public void addScore(int dailyScore) {
		score += dailyScore;
	}

	/**
	 * @return name the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return score the current score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return day the current day the player is on.
	 */
	public int getDay() {
		return day;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Player other = (Player) obj;
		return (other.getName() == this.getName());
	}

}
