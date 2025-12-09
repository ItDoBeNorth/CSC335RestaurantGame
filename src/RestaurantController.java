import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * CSC 335, Fall 2025 file RestaurantController.java: This class is responsible
 * to controls the game and acess the functions through the RestaurantModel.java
 * file.
 * 
 */
public class RestaurantController {
	RestaurantModel model;
	PlayerList playerList;

	/**
	 * creates a new controller of the game
	 * 
	 * @param model a model created from the file RestaurantModel that runs the
	 *              function which the controller display
	 */
	public RestaurantController() {
		model = null;
		File gameData = new File("save_game.dat");
		if (gameData.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(gameData));
				this.playerList = (PlayerList) in.readObject();
				in.close();

			} catch (IOException | ClassNotFoundException e) {

				e.printStackTrace();
			}

		} else {
			playerList = new PlayerList();
		}
	}

	/**
	 * Returns this.playerList
	 * @return PlayerList object
	 */
	public PlayerList getPlayerList() {
		return this.playerList;
	}

	/**
	 * Returns model
	 * @return RestaurantModel
	 */
	public RestaurantModel getModel() {
		return model;
	}

	/**
	 * checks if the day is over
	 * 
	 * @return true if the day is over, false if there are costumers remaining
	 */
	public boolean isDayOver() {
		return model.dayOver();
	}

	/**
	 * moves the player to the next day
	 */
	public void nextDay() {
		if (isDayOver()) {
			model.nextDay();
		}
	}

	/**
	 * get the current customer.
	 * 
	 * @return an array list of the current customers
	 */
	public void getCurrentCustomers() {
		model.updateCustomerQueue();
	}

	/**
	 * get the Ticket of the current customer
	 * 
	 * @param customer the current customer
	 * @return the current Ticket of the current customer
	 */
	public Ticket getCurrentTicket(Customer customer) {
		return model.getCustomerTicket(customer);
	}

	/**
	 * Adds a Topping to the player's basket
	 * 
	 * @param topping chosen topping to be added
	 */
	public void addToBasket(Toppings topping) {
		model.addToBasket(topping);
		;
	}

	/**
	 * Removes topping from model using removeFromBasket()
	 * @param topping Toppings object
	 */
	public void removeFromBasket(Toppings topping) {
		model.removeFromBasket(topping);
	}

	/**
	 * Adds topping to oven in model using addToOven()
	 * @param topping Toppings object
	 */
	public void addToOven(Toppings topping) {
		model.addToOven(topping);
		;
	}

	/**
	 * Removes topping from oven in model using removeFromOven()
	 * @param topping Toppings object
	 */
	public void removeFromOven(Toppings topping) {
		model.removeFromOven(topping);
	}

	/**
	 * Adds a Topping to the burger order
	 * 
	 * @param topping chosen topping to be added
	 */
	public void addToBurger(Toppings topping) {
		model.addToBurger(topping);
	}

	/**
	 * Updates taskList in model using updateTaskList and the customerInt and customer
	 * @param customerInt integer
	 * @param customer Customer object
	 */
	public void updateTaskList(int customerInt, Customer customer) {
		model.updateTaskList(customerInt, customer);
	}

	/**
	 * Calls setUpDay() on model
	 */
	public void setUpDay() {
		model.setUpDay();
	}

	/**
	 * Returns day's toppings using getDaysIngredients() on model
	 * @return ArrayList of Toppings
	 */
	public ArrayList<Toppings> getDaysToppings() {
		return model.getDaysIngredients();
	}

	/**
	 * Calls undoBurger() on model
	 */
	public void undoBurger() {
		model.undoBurger();
	}

	/**
	 * Returns model's basket
	 * @return Basket of Toppings
	 */
	public Basket<Toppings> getCurrBasket() {
		return model.getBasket();
	}

	/**
	 * Returns model's oven
	 * @return Oven of Toppings
	 */
	public Oven<Toppings> getCurrOven() {
		return model.getOven();
	}

	/**
	 * Returns model's Burger
	 * @return Burger object
	 */
	public Burger getBurger() {
		return model.getBurger();
	}

	/**
	 * Clears model's basket
	 */
	public void resetBasket() {
		model.clearBasket();
	}

	/**
	 * reset the burger
	 */
	public void resetBurger() {
		model.resetBurger();
	}

	/**
	 * serve the order to the customer
	 * 
	 * @param ticket the current ticket of the current customer
	 */
	public boolean serveBurger(int ticketInt, Ticket ticket) {
		model.Serve(ticketInt, ticket);
		if (!isDayOver()) {
			model.updateCustomerQueue();
			return true;
		} else {
			System.out.println("Day over");
			model.EODScreen();
			return false;
		}
	}

	/**
	 * process the player name and starts the new day
	 * 
	 * @param player
	 */
	public Player processPlayerName(String playerName) {
		Player currPlayer = playerList.getPlayer(playerName);
		this.model = new RestaurantModel(currPlayer);
		return currPlayer;
	}

	/**
	 * Returns model's days accuracy
	 * @return int
	 */
	public int getDaysAccuracy() {
		return model.getDaysAccuracy();
	}

	/**
	 * Returns model's days timing
	 * @return int
	 */
	public int getDaysTiming() {
		return model.getDaysTiming();
	}

	/**
	 * Returns model's days score
	 * @return int
	 */
	public int getDaysScore() {
		return model.getDaysScore();
	}

	/**
	 * Returns model's days income
	 * @return double
	 */
	public double getDaysIncome() {
		return model.getDaysIncome();
	}

	/**
	 * Returns model's current day
	 * @return int
	 */
	public int getCurrDay() {
		return model.getCurrDay();
	}

	/**
	 * Returns model's day's milestones
	 * @return String
	 */
	public String getDayMilestones() {
		return model.getDayMilestones();
	}

}
