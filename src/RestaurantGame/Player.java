package RestaurantGame;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * CSC 335, Fall 2025 file Player.java: This class is creates a player for the
 * Restaurant game.
 */
public class Player implements Serializable {
	private int day;
	private int score;
	private double money;
	private String name;

	private int customersServed;
	private int customersPoints;
	private int perfectAccuracy;
	private int perfectTiming;

	/**
	 * Creates a new player with a given name starting at day 0 with score 0. Holds
	 * completed days.
	 * 
	 * @param name the name chosen for the player by the user.
	 */
	public Player(String name) {
		this.name = name;
		score = 0;
		day = 0;
		money = 0;

		customersServed = 0;
		customersPoints = 1;
		perfectAccuracy = 0;
		perfectTiming = 0;

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

	public void addCustomerServed() {
		customersServed++;
	}

	/**
	 * get the players daily milestones based on the number of customers served,
	 * streak of perfect accuracy, and streak of perfect timing days and adds points
	 * to the score
	 * 
	 * @return dailyMilestones and array list with the daily milestones
	 */
	public ArrayList<String> milestones() {
		System.out.println(customersServed);
		System.out.println(customersPoints);
		ArrayList<String> dailyMilestones = new ArrayList<>();

		if (customersServed >= 10 * customersPoints) {
			String newMilestone = 10 * customersPoints + "CUSTOMERS" + "(+" + 250 * customersPoints + "pts)";
			dailyMilestones.add(newMilestone);
			addScore(250 * customersPoints);
			customersPoints++;
		}
		if (perfectAccuracy == 10) {
			String newMilestone = "10 Perfect Accuracy days(+1000pts)";
			dailyMilestones.add(newMilestone);
			addScore(1000);
		} else if (perfectAccuracy == 5) {
			String newMilestone = "5 Perfect Accuracy days(+500pts)";
			dailyMilestones.add(newMilestone);
			addScore(500);
		}
		if (perfectTiming == 5) {
			String newMilestone = "5 Perfect Timing days(+1000pts)";
			dailyMilestones.add(newMilestone);
			addScore(1000);
		} else if (perfectTiming == 3) {
			String newMilestone = "3 Perfect Timing days(+500pts)";
			dailyMilestones.add(newMilestone);
			addScore(500);
		}
		return dailyMilestones;
	}

	/**
	 * add a perfect accuracy day
	 */
	public void addPerfectAccuracy() {
		perfectAccuracy++;
	}

	/**
	 * resets perfect accuracy days count
	 */
	public void resetPerfectAccuracy() {
		perfectAccuracy = 0;
	}

	/**
	 * add a perfect timing day
	 */
	public void addPerfectTiming() {
		perfectTiming++;
	}

	/**
	 * resets perfect timing days count
	 */
	public void resetPerfectTiming() {
		perfectTiming = 0;
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
