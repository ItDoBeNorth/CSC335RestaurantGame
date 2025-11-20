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
	private Queue<Customer> daysCustomers;

	private Customer[] currCustomers;
	private Basket basket;
	private Burger burger;
	private ArrayList<Ticket> currTaskList;

	// change later
	private static Toppings[] allToppings;
	private static Customer[] allCustomer;

	RestaurantModel(Player player) {
		// saveLoad from players file but for now pass in a player starting with day one
		day = player.getDay();
		currCustomers = new Customer[2];
		allToppings = IngredientsList.TOPPINGS;
		allCustomer = CustomerList.CUSTOMERS;
	}
	
	
	private boolean dayOver() {
		return (daysCustomers.isEmpty() && currTaskList.isEmpty());
	}

	private void nextDay() {
		day++;
		player.nextDay();
		player.addScore(1);
		daysIngredients = Arrays.copyOfRange(allToppings, 0, day);
		daysCustomers = Collections.shuffle(Arrays.copyOfRange(allCustomer, 0, day));
	}

	public Customer[] updateCustomerQueue() {
		if (currCustomers[1] == null && currCustomers[2] == null) {
			if (daysCustomers.isEmpty()) {
				// controller checks if empty to call next day
				return currCustomers;
			}
		}
		if (currCustomers[1] == null || currCustomers[2] == null) {
			for (int i = 0; i < currCustomers.length; i++) {
				if(currCustomers[i] == null && daysCustomers.peek() != null) {
					currCustomers[i] = daysCustomers.poll();
				}
			}
		}
		return currCustomers;
		
	}
	

	public Ticket getCustomerTicket(Customer customer) {
		ArrayList<Toppings> order = customer.getOrder(daysIngredients, day);
		Ticket ticket = new Ticket(customer, order);
	}

	public void addToBasket(Toppings topping) {
		basket.addIngredient(topping);
	}
	
	public void clearBasket() {
		basket.clearBasket();
	}

	public void addToBurger(Toppings topping) {
		burger.addTopping(topping);
	}
	
	public void resetBurger() {
		burger.reset();
	}
	
	
	public void Serve(Ticket ticket) {
		//should we make player pick character too?
		player.addScore(checkPrecision(ticket));
		// rework currCustomers or find a way to check which customer is to be removed
		currCustomers.remove(ticket.getCustomer());
		daysCustomer.remove(ticket.getCustomer());
		currTaskList.remove(ticket);
		burger.reset();
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
