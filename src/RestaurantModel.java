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

	private ArrayList<Customer> currCustomers;
	private ArrayList<Ticket> currTaskList;
	private Basket<Toppings> basket;
	private Burger burger;

	// change later
	private static Toppings[] allToppings;
	private static Customer[] allCustomer;

	RestaurantModel(Player player) {
		// saveLoad from players file but for now pass in a player starting with day one
		this.player = player;
		day = player.getDay(); // if nextDay is the first thing that is called make sure day-1 OR only save day on end of day
		allToppings = IngredientsList.TOPPINGLIST;
		allCustomer = CustomerList.CUSTOMERS;
		currCustomers = new ArrayList<Customer>();
		currTaskList = new ArrayList<Ticket>();
		
	}
	
	
	private boolean dayOver() {
		return (daysCustomers.isEmpty() && currTaskList.isEmpty());
	}

	private void nextDay() {
		day++;
		player.nextDay();
		player.addScore(1);
		// decide ingredients different if want, for now its by day
		daysIngredients = new ArrayList<Toppings>(Arrays.copyOfRange(Arrays.asList(allToppings), 0, day));
		// customerLimit for now is day, can be changed later
		daysCustomers = Collections.shuffle(Arrays.copyOfRange(allCustomer, 0, day));
		burger.reset();
		basket.clearBasket();
		currTaskList.clear();
		currCustomers.clear();
	}

	public ArrayList<Customer> updateCustomerQueue() {
		if (currCustomers.isEmpty() && daysCustomers.isEmpty()) {
			// controller checks if empty to call next day
			return null;
		}
		if (currCustomers.size() < 2) {
			while (currCustomers.size() < 2 && !daysCustomers.isEmpty()) {
				if (daysCustomers.peek() != null) {
					currCustomers.add(daysCustomers.poll());
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
		
		// remember to add a .equals to customer and ticket
		currCustomers.remove(ticket.getCustomer());
		currTaskList.remove(ticket);
		daysCustomers.remove(ticket.getCustomer());
		burger.reset();
	}

	private int checkPrecision(Ticket ticket) {
		int score = 1;
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
		// maybe using order, correct items, etc.
	}

	
}
