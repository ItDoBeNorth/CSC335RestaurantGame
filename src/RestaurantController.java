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
		playerList = new PlayerList(); // later make it a static and saveload playerList
		model = null;
	}

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

	public void removeFromBasket(int removeInt) {
		model.removeFromBasket(removeInt);
	}

	/**
	 * clears the player's basket
	 */
	public void clearBasket() {
		model.clearBasket();
	}

	/**
	 * Adds a Topping to the burger order
	 * 
	 * @param topping chosen topping to be added
	 */
	public void addToBurger(Toppings topping) {
		model.addToBurger(topping);
	}

	public void updateTaskList(int customerInt, Customer customer) {
		model.updateTaskList(customerInt, customer);
	}

	public ArrayList<Toppings> getDaysToppings() {
		return model.getDaysIngredients();
	}

	public void undoBurger() {
		model.undoBurger();
	}

	public Basket<Toppings> getCurrBasket() {
		return model.getBasket();
	}

	public Burger getBurger() {
		return model.getBurger();
	}
	
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
	public void serveBurger(int ticketInt, Ticket ticket) {
		model.Serve(ticketInt, ticket);
		if (!isDayOver()) {
			model.updateCustomerQueue();
		} else {
			// should implement switching to next day and end of day screen
			System.out.println("Day over");
			nextDay();
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

}
