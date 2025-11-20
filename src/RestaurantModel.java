import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

/**
 * 
 */
public class RestaurantModel {
	private Player player;
	private int day;

	private ArrayList<Toppings> daysIngredients;
	private ArrayList<Customer> daysCustomers;

	private Queue currCustomerQueue;
	private Basket basket;
	private Burger burger;
	private ArrayList<Ticket> currTaskList;

	// change later
	private static ToppingsList[] allToppings;
	private static CustomerList[] allCustomer;

	RestaurantModel(Player player) {
		// saveLoad from players file but for now pass in a player starting with day one
		day = player.getDay();

	}

	private void nextDay() {
		day++;
		player.nextDay();
		player.addScore(1);
		daysIngredients = Arrays.copyOfRange(allToppings, 0, day);
		daysCustomers = Collections.shuffle(Arrays.copyOfRange(allCustomer, 0, day));
	}

	private boolean dayOver() {
		return (daysCustomers.isEmpty() && currTaskList.isEmpty());
	}

	private Ticket getCustomerTicket(Customer customer) {
		ArrayList<Toppings> order = customer.getOrder(daysIngredients, day);
		Ticket ticket = new Ticket(customer, order);
	}

	private void addToBasket(Toppings topping) {
		basket.addTopping(topping);
	}

	private void addToBurger(Toppings topping) {
		burger.addTopping(topping);
	}

	private void Serve(Ticket ticket) {
		//should we make player pick character too?
		player.addScore(checkPrecision(ticket));
		currCustomerQueue.remove(ticket.getCustomer());
		daysCustomers.remove(ticket.getCustomer());
		currTaskList.remove(ticket);
	}

	private int checkPrecision(ticket) {
		score = 1;
		// implement a .equals if needed
		
		// add some trouble shooting stuff like check size, order of ingredients
		// here is only the implementation for checking if its exact
		for (int i = 0; i < ticket.getOrderSize(); i++) {
			if (ticket.getToppingsList.get(i) != burger.getToppings.get(i)) {
				score = 0;
			}
		}
		// find other way to evaluate score
		return score;
	}

	
}
