import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;


/** 
 * setChanged(); notifyObservers(new EventDetail());
 */
@SuppressWarnings("deprecation")
public class RestaurantModel extends Observable {
	private Player player;
	
	private static Random r = new Random();

	private ArrayList<Toppings> daysIngredients;
	private Queue<Customer> daysCustomers;
	
	// things GUI should know
	private int currDay;
	private Customer[] currCustomers;
	private Ticket[] currTaskList;
	private Basket<Toppings> basket;
	private Oven<Toppings> oven;
	private Burger burger;
	
	private int daysAccuracy;
	private int daysTiming;
	private double daysIncome;
	private int daysScore;
	private ArrayList<String> dayMilestones;
	
	private int customersServed;
	private String[] newThings = {"New Ingredient: Lettuce\nNew Customer: John(Generous)", "New Ingredient: Onion\nNew Customer: Peter(Hurry)", "New Ingredient: Pickle\nNew Customer: Mariah(Picky)", "New Ingredient: Tomato\nNew Customer: David(Hurry)", "New Customer: Sarah(Patient)"};
	// new things how

	// change later
	private static Toppings[] allToppings;
	private static Customer[] allCustomer;

	RestaurantModel(Player player) {
		// saveLoad from players file but for now pass in a player starting with day one
		this.player = player;
		currDay = player.getDay(); // if nextDay is the first thing that is called make sure day-1 OR only save day
								// on end of day

		daysIngredients = new ArrayList<Toppings>();
		daysCustomers = new LinkedList<Customer>();

		currCustomers = new Customer[2];
		currTaskList = new Ticket[2];
		burger = new Burger();
		basket = new Basket<Toppings>(10);
		oven=new Oven<Toppings>(5);

		allToppings = IngredientsList.TOPPINGLIST;
		allCustomer = CustomerList.CUSTOMERS;
		
		daysAccuracy = 0;
		daysTiming = 0;
		daysIncome = 0.0;
		daysScore = 0;
		customersServed = 0;

	}

	public boolean dayOver() {
		// add something here for player day end score and stuff
		return (daysCustomers.isEmpty() && currCustomers[0] == null && currCustomers[1] == null);
	}

	public void EODScreen() {
		daysAccuracy = daysAccuracy/(customersServed*3);
		daysTiming = daysTiming/customersServed;

		if (daysAccuracy==100) {
			player.addPerfectAccuracy();
		}
		else {
			player.resetPerfectAccuracy();
		}
		if (daysTiming==100) {
			player.addPerfectTiming();
		}
		else {
			player.resetPerfectTiming();
		}
		dayMilestones=player.milestones();
		setChanged();
		notifyObservers(new EventDetail("updateEndOfDayScreen", newThings[Math.min(currDay-1, allToppings.length)]));
	}
	
	public void nextDay() {
		player.nextDay();
		player.addScore(1);
		setUpDay();
	}
	
	public void setUpDay() {
		daysAccuracy = 0;
		daysTiming = 0;
		daysIncome = 0.0;
		daysScore = 0;
		customersServed = 0;
		currDay++;
		// decide ingredients different if want, for now its by day
		daysIngredients = new ArrayList<Toppings>(Arrays.asList(Arrays.copyOfRange(allToppings, 0, Math.min(currDay, allToppings.length))));
		if (currDay > 5) {
			daysIngredients.add(new Patty());
		}
		System.out.println(daysIngredients.size());
		// customerLimit for now is day, can be changed later
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>(
				Arrays.asList(Arrays.copyOfRange(allCustomer, 0, 1 + Math.min(currDay, allCustomer.length))));
		if (currDay > 9) {
			for (int i = 0; i < (currDay-9); i++) {
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
		ArrayList<Toppings> order = customer.getOrder(daysIngredients, currDay);
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
	public void removeFromOven(Toppings topping) {
		Toppings currIngredient=oven.remove(topping);
		Patty currPatty=(Patty)currIngredient;
		currPatty.stopCooking();
		basket.addIngredient(currIngredient);
		
		setChanged();
		notifyObservers(new EventDetail("updateOven", oven));
		notifyObservers(new EventDetail("updateBasket", basket));
	}

	public void addToOven(Toppings topping) {
		
		if (oven.addIngredient(topping)) {
			Patty currPatty=(Patty)topping;
			currPatty.startCooking();
			setChanged(); 
			notifyObservers(new EventDetail("updateOven", oven));
		}
	}
	public void clearOven() {
		oven.clearOven();
		setChanged();
		notifyObservers(new EventDetail("updateOven", oven));
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
		customersServed ++;
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
		int patienceTime = 10 + ( ticket.getCustomer().patienceLevel() * currDay);
		
		// 100 points max from patience time 
		if (elapsed > patienceTime){
			// percent over to the limit of double the patience time, the longer it goes on after the lower the points 
			double percent = (patienceTime*2 - Math.min(elapsed,patienceTime*2))/patienceTime;
			timing = (int) Math.max(0, Math.round(percent * 50));
		} else {timing = 100;}
		
		// max of 250 points from the order
		if (!isTheSameOrder(ticket.getToppingsList(),burger.getToppings())) {
			// num of items, if correct items are present, and an estimate on order without the correct serve
			ArrayList<Toppings> ordered =  new ArrayList<Toppings>(ticket.getToppingsList());
			ArrayList<Toppings> served = new ArrayList<Toppings>(burger.getToppings());
			// 50 points from correct num of items
			int numItems = (int) Math.max(0,50- Math.round((Math.abs(ordered.size()-served.size())/(double)ordered.size()) * 50)) ;
			// correct kinds of items
			int contains = 0;
			for (Toppings t: ordered) {
				if (served.contains(t)) {contains++;}
			}
			// 100 points from if correct items are contained
			int itemsS = (int) Math.round((contains/(double)ordered.size()) * 100);
			
			int order = 0;
			// estimate on order
			if (numItems + itemsS > 100) {order = 50;}
			accuracy = numItems + itemsS + order;
			if (p == KnownCustomer.Personality.ACCURATE) {
				// more accurate needed for customer
				if (accuracy < 200) {
					System.out.println(accuracy);
					accuracy = 0;
				}
			}
		} else {
			accuracy = 250;
		}
		
		//50 points from accuracy of the patties
		accuracy += pattyAccuracy(new ArrayList<Toppings>(burger.getToppings()));
		
		// income based on total accuracy and price of ingredients
		income = 5 + (getPrice(burger.getToppings()) * ((accuracy+timing)/400.0));
		if (p == KnownCustomer.Personality.GENEROUS) {
			income = income * 2;
		}
		
		// add to where its needed
		daysAccuracy += accuracy;
		daysIncome += income;
		daysTiming += timing;
		score += accuracy+timing;
		daysScore += score;
		player.addScore(score);
		player.addMoney(income);
		
	}

	public int pattyAccuracy(ArrayList<Toppings> served) {
		// checks for patties
		int undercooked = 0;
		int cooked = 0;
		int overcooked = 0;
		for (Toppings p: served) {
			if (p instanceof Patty) {
				if (((Patty) p).getCookingState() == Patty.CookingState.UNDERCOOKED){
					undercooked ++;
				} else if ( (((Patty) p).getCookingState() == Patty.CookingState.COOKED)) {
					cooked ++;
				} else {
					overcooked ++;
				}
			}
		}
		// scores accordingly, 50 for all cooked, 25 for all burnt, 15 for all uncooked
		int total = undercooked+cooked+overcooked;
		System.out.println("total = " +total);
		if (total == 0) {return 50;}
		int temp = (int) ((50*((double)cooked/total))+(15*((double)undercooked/total))+(25*((double)overcooked/total)));
		System.out.println("temp = " + temp);
		return temp;
	}
	
	public double getPrice(ArrayList<Toppings> toppings) {
		double amount = 0;
		for (Toppings t: toppings) {
			amount += t.getPrice();
		}
		return amount;
	}
	
	public boolean isTheSameOrder(ArrayList<Toppings> ticketIngredients,ArrayList<Toppings> orderIngredients) {
		if(ticketIngredients.size()!=orderIngredients.size()) {
			return false;
		}
		for (int i=0;i<ticketIngredients.size();i++) {
			if (!ticketIngredients.get(i).isTheSameIngredient(orderIngredients.get(i))){
				return false;
			}
		}
		return true;
	}
	
	public Basket<Toppings> getBasket() {
		return basket;
	}
	public Oven<Toppings> getOven(){
		return oven;
	}

	public Burger getBurger() {
		return burger;
	}

	public ArrayList<Toppings> getDaysIngredients() {
		return daysIngredients;
	}

	public int getDaysAccuracy() {
		return daysAccuracy;
	}

	public int getDaysTiming() {
		return daysTiming;
	}

	public int getDaysScore() {
		return daysScore;
	}

	public double getDaysIncome() {
		return daysIncome;
	}
	public String getDayMilestones(){
		String milestoneStr="";
		for(int i=0;i<dayMilestones.size();i++) {
			milestoneStr+=dayMilestones.get(i)+"\n";
		}
		milestoneStr.trim();
		return milestoneStr;
		
	}
	public int getCurrDay() {
		return currDay;
	}
}
