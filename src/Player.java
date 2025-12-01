import java.io.Serializable;

public class Player implements Serializable {
	private int day;
	private int score;
	private double money;
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
		money = 0;
	}

	/**
	 * Creates a player with a given name, day, and score.
	 * 
	 * @param name  the name chosen for the player by the user.
	 * @param day   the current day the player is on.
	 * @param score the current score of the player.
	 * @param money 
	 */
	public Player(String name, int day, int score, int money) {
		this.name = name;
		this.day = day;
		this.score = score;
		this.money = money;
	}

	/**
	 * move the player to the next day
	 */
	public void nextDay() {
		day++;
	}

	/**
	 * adds the score to player's score
	 * 
	 * @param the score earned
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * adds the income score to player's money
	 * 
	 * @param the income earned
	 */
	public void addMoney(double income) {
		this.money += income;
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
	 * @return current money of the player
	 */
	public double getMoney() {
		return money;
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
