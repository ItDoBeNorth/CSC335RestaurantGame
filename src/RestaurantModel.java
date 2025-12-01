import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 * setChanged(); notifyObservers(new EventDetail());
 */
@SuppressWarnings("deprecation")
public class RestaurantModel extends Observable {
	private Player player;

	private ArrayList<Toppings> daysIngredients;
	private Queue<Customer> daysCustomers;

	// things GUI should know
	private int day;
	private Customer[] currCustomers;
	private Ticket[] currTaskList;
	private Basket<Toppings> basket;
	private Burger burger;

	// change later
	private static Toppings[] allToppings;
	private static Customer[] allCustomer;

	RestaurantModel(Player player) {
		// saveLoad from players file but for now pass in a player starting with day one
		this.player = player;
		day = player.getDay(); // if nextDay is the first thing that is called make sure day-1 OR only save day
								// on end of day

		daysIngredients = new ArrayList<Toppings>();
		daysCustomers = new LinkedList<Customer>();

		currCustomers = new Customer[2];
		currTaskList = new Ticket[2];
		burger = new Burger();
		basket = new Basket<Toppings>(10);

		allToppings = IngredientsList.TOPPINGLIST;
		allCustomer = CustomerList.CUSTOMERS;

	}

	public boolean dayOver() {
		// add something here for player day end score and stuff
		return (daysCustomers.isEmpty() && currCustomers[0] == null && currCustomers[1] == null);
	}

	public void EODScreen() {
		setChanged();
		notifyObservers(new EventDetail("updateEndOfDayScreen", null));
	}
	
	public void nextDay() {
		day++;
		player.nextDay();
		player.addScore(1);
		// decide ingredients different if want, for now its by day
		daysIngredients = new ArrayList<Toppings>(Arrays.asList(Arrays.copyOfRange(allToppings, 0, Math.min(day, allToppings.length))));
		// customerLimit for now is day, can be changed later
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>(
				Arrays.asList(Arrays.copyOfRange(allCustomer, 0, 1 + Math.min(day, allCustomer.length))));
		Collections.shuffle(tempCustomers);
		daysCustomers = new LinkedList<Customer>(tempCustomers);
		setChanged();
		notifyObservers(new EventDetail("daysIngredients", daysIngredients));
		resetBurger();
		clearBasket();
		currTaskList = new Ticket[2];
		setChanged();
		notifyObservers(new EventDetail("resetTickets", currTaskList));
		currCustomers = new Customer[2];
		setChanged();
		notifyObservers(new EventDetail("resetCustomers", currCustomers));
		updateCustomerQueue();
	}

	public void updateCustomerQueue() {
		if (daysCustomers.isEmpty()) {
			return;
		}
		for (int i = 0; i < 2; i++) {
			if (currCustomers[i] == null && daysCustomers.peek() != null) {
				currCustomers[i] = (daysCustomers.poll());
				setChanged();
				notifyObservers(new EventDetail("customerQueueUpdate" + i, currCustomers));
			}
		}
		
	}

	public Ticket getCustomerTicket(Customer customer) {
		ArrayList<Toppings> order = customer.getOrder(daysIngredients, day);
		Ticket ticket = new Ticket(customer, order);
		return ticket;
	}

	public void updateTaskList(int customerInt, Customer customer) {
		currTaskList[customerInt] = getCustomerTicket(customer);
		setChanged();
		notifyObservers(new EventDetail("currTasksChanged" + customerInt, currTaskList));
	}

	public void removeFromBasket(Toppings topping) {
		basket.remove(topping);
		setChanged();
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	public void addToBasket(Toppings topping) {
		if (basket.addIngredient(topping)) {
			setChanged(); 
			notifyObservers(new EventDetail("updateBasket", basket));
		}
	}

	public void clearBasket() {
		basket.clearBasket();
		setChanged();
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	public void undoBurger() {
		if (!burger.getToppings().isEmpty()) {
			basket.limit += 1;
			System.out.println(basket.addIngredient(burger.RemoveLastTopping()));
			basket.limit = 10;
			setChanged();
			notifyObservers(new EventDetail("updateBasket", basket));
			setChanged();
			notifyObservers(new EventDetail("updateBurger", null));
		}
	}

	public void addToBurger(Toppings topping) {
		burger.addTopping(topping);
		setChanged();
		notifyObservers(new EventDetail("updateBurger", null));
	}

	public void resetBurger() {
		//basket.limit += burger.getToppings().size();
		for (Toppings t : burger.getToppings()) {
			basket.addIngredient(t);
		}
		burger.reset();
		//basket.limit = 10;
		setChanged();
		notifyObservers(new EventDetail("updateBurger", null));
		setChanged();
		notifyObservers(new EventDetail("updateBasket", null));
	}

	public void Serve(int ticketInt, Ticket ticket) {
		// should we make player pick character too?
		player.addScore(checkPrecision(ticket));
		// ADD notify if gui implemented for score
		// ADD time precision
		// ADD price of burger

		currCustomers[ticketInt] = null;
		setChanged();
		notifyObservers(new EventDetail("removeCustomer" + ticketInt, currCustomers));
		currTaskList[ticketInt] = null;
		setChanged();
		notifyObservers(new EventDetail("removeTask" + ticketInt, currTaskList));
		burger.reset();
		setChanged();
		notifyObservers(new EventDetail("updateBurger", null));
	}

	private int checkPrecision(Ticket ticket) {
		int score = 1;
		// implement a .equals if needed

		// add some trouble shooting stuff like check size, order of ingredients
		// here is only the implementation for checking if its exact
//		for (int i = 0; i < ticket.getOrderSize(); i++) {
//			// need null pointer exception checks
//			if (ticket.getToppingsList().get(i) != burger.getToppings().get(i)) {
//				score = 0;
//			}
//		}
		if (ticket.getToppingsList() != burger.getToppings()) {score = 0;} 
		// find other way to evaluate score
		return score;
		// maybe using order, correct items, etc.
	}

	public Basket<Toppings> getBasket() {
		return basket;
	}

	public Burger getBurger() {
		return burger;
	}

	public ArrayList<Toppings> getDaysIngredients() {
		return daysIngredients;
	}
}