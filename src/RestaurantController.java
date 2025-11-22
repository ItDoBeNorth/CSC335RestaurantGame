import java.util.ArrayList;
/**
 * CSC 335, Fall 2025
 * file RestaurantController.java: This class is responsible to controls the game
 * and acess the functions through the RestaurantModel.java file.
 * 
 */
public class RestaurantController {
	RestaurantModel model;
	/**
	 * creates a new controller of the game 
	 * @param model a model created from the file RestaurantModel that runs the function
	 * which the controller display
	 */
	public RestaurantController(RestaurantModel model ) {
		this.model=model;
	}
	/**
	 * get the current customer.
	 * @return an array list of the current customers
	 */
	public ArrayList<Customer> getCurrentCustomer() {
		return model.updateCustomerQueue();
	}
	/**
	 * get the Ticket of the current customer
	 * @param customer the current customer
	 * @return the current Ticket of the current customer
	 */
	public Ticket getCurrentTicket(Customer customer) {
		return model.getCustomerTicket(customer);
	}
	/**
	 * Adds a Topping to the player's basket
	 * @param topping chosen topping to be added
	 */
	public void addToBasket(Toppings topping) {
		model.addToBasket(topping);;
	}
	/**
	 * clears the player's basket
	 */
	public void clearBasket() {
		model.clearBasket();
	}
	/**
	 * Adds a Topping to the burger order
	 * @param topping chosen topping to be added
	 */
	public void addToBurger(Toppings topping) {
		model.addToBurger(topping);
	}
	/**
	 * reset the burger 
	 */
	public void resetBurger() {
		model.resetBurger();;
	}
	/**
	 * serve the order to the customer
	 * @param ticket the current ticket of the current customer
	 */
	public void serveOrder(Ticket ticket) {
		model.Serve(ticket);
	}
	
}
