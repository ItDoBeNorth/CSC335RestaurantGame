import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;

/**
 * RestaurantModel provides variables and methods to represent the player for the Restaurant, the current Customers and Tickets for a given day,
 * the various Toppings used to fulfill Tickets, and variables to track the score, timing, income, and milestones for a day. It can set up a day in the game and provides 
 * the underlying implementation to interact with the GUI screens, calculate scores, and keep the game loop running.
 */
@SuppressWarnings("deprecation")
public class RestaurantModel extends Observable {
	/**
	 * Player object for this instance of restaurant game
	 */
	private Player player;

	/**
	 * Random variable for this RestaurantModel
	 */
	private static Random r = new Random();

	/**
	 * An ArrayList of Toppings that corresponds to the Toppings needed to build this day's burgers
	 */
	private ArrayList<Toppings> daysIngredients;
	/**
	 * A Queue of Customers that holds the customers of the Restaurant on this day
	 */
	private Queue<Customer> daysCustomers;

	// things GUI should know
	/**
	 * integer representation of which day it is
	 */
	private int currDay;
	/**
	 * Customer array of the current customers
	 */
	private Customer[] currCustomers;
	/**
	 * Ticket array of the current tickets
	 */
	private Ticket[] currTaskList;
	/**
	 * Basket of Toppings that holds Toppings on prep screen
	 */
	private Basket<Toppings> basket;
	/**
	 * Oven of Toppings that holds Toppings on prep screen
	 */
	private Oven<Toppings> oven;
	/**
	 * Burger object that represents the current Burger being prepared
	 */
	private Burger burger;

	/**
	 * integer of the accuracy for given day
	 */
	private int daysAccuracy;
	/**
	 * integer of timing for given day
	 */
	private int daysTiming;
	/**
	 * double of income for given day
	 */
	private double daysIncome;
	/**
	 * integer of score for given day
	 */
	private int daysScore;
	/**
	 * ArrayList of Strings for the day's milestones
	 */
	private ArrayList<String> dayMilestones;

	/**
	 * integer for number of customers served
	 */
	private int customersServed;
	/**
	 * String array for new things added in as the game progresses
	 */
	private String[] newThings = { "New Ingredient: Lettuce\nNew Customer: John(Generous)",
			"New Ingredient: Onion\nNew Customer: Peter(Hurry)", "New Ingredient: Pickle\nNew Customer: Mariah(Picky)",
			"New Ingredient: Tomato\nNew Customer: David(Hurry)", "New Customer: Sarah(Patient)",
			"One more customer, One more patty", "One more customer" };

	/**
	 * Toppings array for all possible toppings
	 */
	private static Toppings[] allToppings;
	/**
	 * Customer array for all possible customers
	 */
	private static Customer[] allCustomer;

	/**
	 * Initializes instance of Restaurant Model. Sets this.player to player, currDay to player.getDay(), initializes daysIngredients,
	 * daysCustomers, and burger. currCustomers and currTaskList are both arrays of size 2, basket is a Basket, oven is an Oven, both of Toppings.
	 * allToppings is set to the topping list from the IngredientsList class, allCustomer is set to the customer list from CustomerList class.
	 * @param player a Player object
	 */
	RestaurantModel(Player player) {
		this.player = player;
		currDay = player.getDay(); // player holds on to completed days

		daysIngredients = new ArrayList<Toppings>();
		daysCustomers = new LinkedList<Customer>();

		currCustomers = new Customer[2];
		currTaskList = new Ticket[2];
		burger = new Burger();
		basket = new Basket<Toppings>(10);
		oven = new Oven<Toppings>(5);

		allToppings = IngredientsList.TOPPINGLIST;
		allCustomer = CustomerList.CUSTOMERS;

		daysAccuracy = 0;
		daysTiming = 0;
		daysIncome = 0.0;
		daysScore = 0;
		customersServed = 0;

	}

	/**
	 * Returns whether the day is over. Returns true if there are no more customers to serve, false otherwise
	 * @return boolean whether there are more customers to serve
	 */
	public boolean dayOver() {
		return (daysCustomers.isEmpty() && currCustomers[0] == null && currCustomers[1] == null);
	}

	/**
	 * Updates the End of Day Screen based on player's performance. Checks whether the player has perfect accuracy or timing and updates the variable for player. 
	 */
	public void EODScreen() {
		daysAccuracy = daysAccuracy / (customersServed * 3);
		daysTiming = daysTiming / customersServed;

		if (daysAccuracy == 100) {
			player.addPerfectAccuracy();
		} else {
			player.resetPerfectAccuracy();
		}
		if (daysTiming == 100) {
			player.addPerfectTiming();
		} else {
			player.resetPerfectTiming();
		}
		dayMilestones = player.milestones();
		setChanged();
		notifyObservers(
				new EventDetail("updateEndOfDayScreen", newThings[Math.min(currDay - 1, newThings.length - 1)]));
	}

	/**
	 * Sets up restaurant for next day. Calls player.nextDay() and increments the player score by 1, then calls setUpDay().
	 */
	public void nextDay() {
		player.nextDay();
		player.addScore(1);
		setUpDay();
	}

	/**
	 * Resets day variables and makes new Customers and Ingredients for next day. Clears Oven, Burger, and Basket,
	 * sets daysAccuracy, daysTiming, daysIncome, daysScore, customersServed to 0, increments currDay by 1. Changes
	 * dayCustomers, currTaskList, and currCustomers to fit newly made Customer/Ingredient lists. 
	 */
	public void setUpDay() {
		daysAccuracy = 0;
		daysTiming = 0;
		daysIncome = 0.0;
		daysScore = 0;
		customersServed = 0;
		currDay++;
		
		// ingredients and customers change by day
		daysIngredients = new ArrayList<Toppings>(
				Arrays.asList(Arrays.copyOfRange(allToppings, 0, Math.min(currDay, allToppings.length))));
		
		if (currDay > 5) {
			daysIngredients.add(new Patty());
		}
		
		// clear oven
		clearOven();
		
		// customerLimit for now is day, can be changed later
		System.out.println(daysIngredients.size());
		
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>(
				Arrays.asList(Arrays.copyOfRange(allCustomer, 0, 1 + Math.min(currDay, allCustomer.length - 1))));
		
		// adds new random customers after day 9
		if (currDay > 9) {
			for (int i = 0; i < (currDay - 9); i++) {
				tempCustomers.add(allCustomer[r.nextInt(allCustomer.length)]);
			}
		}
		
		System.out.println(tempCustomers.size());
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

	/**
	 * Sets currCustomer to next customer. If there are no more customers for the day, returns. 
	 */
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

	/**
	 * Returns the Ticket object that corresponds to given customer. Takes the potential toppings for the customer
	 * given the day and makes a Ticket object out of it.
	 * @param customer Customer object
	 * @return Ticket object of customer's order
	 */
	public Ticket getCustomerTicket(Customer customer) {
		ArrayList<Toppings> order = customer.getOrder(daysIngredients, currDay);
		Ticket ticket = new Ticket(customer, order);
		return ticket;
	}

	/**
	 * Updates the currTaskList that corresponds with customer's number to that customer's order. 
	 * @param customerInt integer corresponding to customer's number
	 * @param customer Customer object 
	 */
	public void updateTaskList(int customerInt, Customer customer) {
		currTaskList[customerInt] = getCustomerTicket(customer);
		setChanged();
		notifyObservers(new EventDetail("currTasksChanged" + customerInt, currTaskList));
	}

	/**
	 * Removes topping from basket. Updates the basket accordingly
	 * @param topping Topping object
	 */
	public void removeFromBasket(Toppings topping) {
		basket.remove(topping);
		setChanged();
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	/**
	 * Adds topping to basket. Updates basket accordingly
	 * @param topping Topping object
	 */
	public void addToBasket(Toppings topping) {
		if (basket.addIngredient(topping)) {
			setChanged();
			notifyObservers(new EventDetail("updateBasket", basket));
		}
	}

	/**
	 * Removes Topping, which is a Patty, from oven, stops cooking it, and adds it to basket. Updates the oven and basket accordingly
	 * @param topping Topping object, must be a Patty
	 */
	public void removeFromOven(Toppings topping) {
		Toppings currIngredient = oven.remove(topping);
		Patty currPatty = (Patty) currIngredient;
		currPatty.stopCooking();
		basket.addIngredient(currIngredient);

		setChanged();
		notifyObservers(new EventDetail("updateOven", oven));
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	/**
	 * Adds topping, which must be a Patty, to the oven. Starts cooking the Patty and updates the oven accordingly
	 * @param topping Topping object
	 */
	public void addToOven(Toppings topping) {

		if (oven.addIngredient(topping)) {
			Patty currPatty = (Patty) topping;
			currPatty.startCooking();
			setChanged();
			notifyObservers(new EventDetail("updateOven", oven));
		}
	}

	/**
	 * Calls clearOven() on oven. Updates the oven accordingly
	 */
	public void clearOven() {
		oven.clearOven();
		setChanged();
		notifyObservers(new EventDetail("updateOven", oven));
	}

	/**
	 * Clears the basket by calling clearBasket() on basket. Updates the basket accordingly
	 */
	public void clearBasket() {
		basket.clearBasket();
		setChanged();
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	/**
	 * Removes last topping added to the burger. If the burger has at least one Topping, increases the basket limit by 1, removes the last topping added to burger
	 * and adds it to basket, and changes the basket limit to 10. Updates the basket and burger accordingly. 
	 */
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

	/**
	 * Adds topping to burger via addTopping(). Updates burger accordingly
	 * @param topping Topping object
	 */
	public void addToBurger(Toppings topping) {
		burger.addTopping(topping);
		setChanged();
		notifyObservers(new EventDetail("updateBurger", null));
	}

	/**
	 * Clears burger of toppings. For every topping on burger, adds it to basket. Updates the burger and basket accordingly.
	 */
	public void resetBurger() {
		for (Toppings t : burger.getToppings()) {
			basket.addIngredient(t);
		}
		burger.reset();
		setChanged();
		notifyObservers(new EventDetail("updateBurger", null));
		setChanged();
		notifyObservers(new EventDetail("updateBasket", null));
	}

	/**
	 * Serves burger to customer ticketInt with given ticket. Increases customersServed++ and calls checkScore(ticket) for ticket. 
	 * Sets currCustomers[ticketInt] and currTaskList[ticketInt] to null and resets burger, then removes customer and task and updates burger.
	 * @param ticketInt integer
	 * @param ticket Ticket object
	 */
	public void Serve(int ticketInt, Ticket ticket) {
		customersServed++;
		player.addCustomerServed();
		checkScore(ticket);

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

	/**
	 * Using ticket, calculates the score for that ticket based off how quickly and how accurately it was fulfilled. Using the Personality of the customer associated
	 * with ticket (if there is one) calculates the timing score based on whether ticket was finished faster than the customer's patience. Next, looks at if the fulfilled
	 * order had the same number and type of toppings and whether the patties were cooked properly. These factors contribute to overall score, which is then used
	 * to update income.
	 * @param ticket Ticket object
	 */
	private void checkScore(Ticket ticket) {
		int score = 1;
		int accuracy = 0;
		double income = 0;
		int timing = 0;
		KnownCustomer.Personality p = null;
		
		// for stuff based on customer
		if (ticket.getCustomer() instanceof KnownCustomer) {
			p = ((KnownCustomer) ticket.getCustomer()).getPersonality();
		}

		// get patienceTime and how much time prepping the order took
		int elapsed = ticket.stopCountDown();
		int patienceTime = 10 + (ticket.getCustomer().patienceLevel() * currDay);

		// 100 points max from patience time
		if (elapsed > patienceTime) {
			// percent over to the limit of double the patience time, the longer it goes on
			// after the lower the points
			double percent = (patienceTime * 2 - Math.min(elapsed, patienceTime * 2)) / patienceTime;
			timing = (int) Math.max(0, Math.round(percent * 50));
		} else {
			timing = 100;
		}

		// max of 250 points from the order
		if (!isTheSameOrder(ticket.getToppingsList(), burger.getToppings())) {
			// num of items, if correct items are present, and an estimate on order without
			// the correct serve
			ArrayList<Toppings> ordered = new ArrayList<Toppings>(ticket.getToppingsList());
			ArrayList<Toppings> served = new ArrayList<Toppings>(burger.getToppings());
			// 50 points from correct num of items
			int numItems = (int) Math.max(0,
					50 - Math.round((Math.abs(ordered.size() - served.size()) / (double) ordered.size()) * 50));
			// correct kinds of items
			int contains = 0;
			for (Toppings t : ordered) {
				if (served.contains(t)) {
					contains++;
				}
			}
			// 100 points from if correct items are contained
			int itemsS = (int) Math.round((contains / (double) ordered.size()) * 100);

			int order = 0;
			// estimate on order
			if (numItems + itemsS > 100) {
				order = 50;
			}
			accuracy = numItems + itemsS + order;
			if (p == KnownCustomer.Personality.ACCURATE) {
				// harsher accuracy
				if (accuracy < 200) {
					System.out.println(accuracy);
					accuracy = 0;
				}
			}
		} else {
			accuracy = 250;
		}

		// 50 points from accuracy of the patties
		accuracy += pattyAccuracy(new ArrayList<Toppings>(burger.getToppings()));

		// income based on total accuracy and price of ingredients
		income = 5 + (getPrice(burger.getToppings()) * ((accuracy + timing) / 400.0));
		if (p == KnownCustomer.Personality.GENEROUS) {
			income = income * 2;
		}

		// add all calculated amounts to where its needed
		daysAccuracy += accuracy;
		daysIncome += income;
		daysTiming += timing;
		score += accuracy + timing;
		daysScore += score;
		player.addScore(score);
		player.addMoney(income);
	}

	/**
	 * Returns score for patties in a burger based on how well they were cooked. Looks at all Patty objects that were served, tallies how many were 
	 * undercooked, cooked, and overcooked, and uses those tallies to calculate overall score.
	 * @param served ArrayList of Toppings
	 * @return int score from Pattys that were served
	 */
	public int pattyAccuracy(ArrayList<Toppings> served) {
		// checks for patties
		int undercooked = 0;
		int cooked = 0;
		int overcooked = 0;
		for (Toppings p : served) {
			if (p instanceof Patty) {
				if (((Patty) p).getCookingState() == Patty.CookingState.UNDERCOOKED) {
					undercooked++;
				} else if ((((Patty) p).getCookingState() == Patty.CookingState.COOKED)) {
					cooked++;
				} else {
					overcooked++;
				}
			}
		}
		// scores accordingly, 50 for all cooked, 25 for all burnt, 15 for all uncooked
		int total = undercooked + cooked + overcooked;
		System.out.println("total = " + total);
		if (total == 0) {
			return 50;
		}
		int temp = (int) ((50 * ((double) cooked / total)) + (15 * ((double) undercooked / total))
				+ (25 * ((double) overcooked / total)));
		System.out.println("temp = " + temp);
		return temp;
	}

	/**
	 * Gets price for list of toppings. Goes through list of Toppings and adds the price of each one together
	 * @param toppings ArrayList of Toppings
	 * @return double price of all Toppings
	 */
	public double getPrice(ArrayList<Toppings> toppings) {
		double amount = 0;
		for (Toppings t : toppings) {
			amount += t.getPrice();
		}
		return amount;
	}

	/**
	 * Returns true if ticketIngredients is the same as orderIngredients, false otherwise. Compares the size of the ArrayLists and then each
	 * individual entry
	 * @param ticketIngredients ArrayList of Toppings
	 * @param orderIngredients ArrayList of Toppings
	 * @return boolean true if ArrayLists are the same, false otherwise
	 */
	public boolean isTheSameOrder(ArrayList<Toppings> ticketIngredients, ArrayList<Toppings> orderIngredients) {
		if (ticketIngredients.size() != orderIngredients.size()) {
			return false;
		}
		for (int i = 0; i < ticketIngredients.size(); i++) {
			if (!ticketIngredients.get(i).isTheSameIngredient(orderIngredients.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return basket
	 * @return Basket of Toppings
	 */
	public Basket<Toppings> getBasket() {
		return basket;
	}

	/**
	 * Return oven
	 * @return Oven of Toppings
	 */
	public Oven<Toppings> getOven() {
		return oven;
	}

	/**
	 * Return burger
	 * @return Burger object
	 */
	public Burger getBurger() {
		return burger;
	}

	/**
	 * Return daysIngredients
	 * @return ArrayList of Toppings
	 */
	public ArrayList<Toppings> getDaysIngredients() {
		return daysIngredients;
	}

	/**
	 * Return currCustomers
	 * @return Customer array
	 */
	public Customer[] getCurrCustomers() {
		return currCustomers;
	}

	/**
	 * Return daysAccuracy
	 * @return integer
	 */
	public int getDaysAccuracy() {
		return daysAccuracy;
	}

	/**
	 * Return daysTiming
	 * @return integer
	 */
	public int getDaysTiming() {
		return daysTiming;
	}

	/**
	 * Return daysScore
	 * @return integer
	 */
	public int getDaysScore() {
		return daysScore;
	}

	/**
	 * Return daysIncome
	 * @return double
	 */
	public double getDaysIncome() {
		return daysIncome;
	}

	/**
	 * Return String of all the day's milestones
	 * @return String
	 */
	public String getDayMilestones() {
		String milestoneStr = "";
		for (int i = 0; i < dayMilestones.size(); i++) {
			milestoneStr += dayMilestones.get(i) + "\n";
		}
		milestoneStr.trim();
		return milestoneStr;

	}

	/**
	 * Return currDay
	 * @return integer
	 */
	public int getCurrDay() {
		return currDay;
	}
}
