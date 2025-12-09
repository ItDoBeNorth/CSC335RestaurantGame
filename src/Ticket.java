import java.util.ArrayList;

/**
 * A Ticket represents a customer's order. It stores the customer who created
 * the ticket, the list of toppings they wanted, and a stopwatch timer
 * that measures how long the user takes to complete the order.
 */
public class Ticket {
	private ArrayList<Toppings> ingredients;
	private Customer thisCustomer;
	private Countdown stopwatch;
	
	/**
     * Creates a new Ticket for a specific customer with a list of toppings.
     * The stopwatch begins immediately after creation.
     *
     * @param thisCustomer: The customer who created the ticket
     * @param ingredients: The list of toppings in the order
     */
	public Ticket(Customer thisCustomer, ArrayList<Toppings> ingredients) {
		this.thisCustomer = thisCustomer;
		this.ingredients = ingredients;
		this.stopwatch = new Countdown();
		stopwatch.startStopwatch();
	}
	
	 /**
     * Stops the stopwatch and returns the total elapsed time.
     *
     * @return the number of seconds elapsed since the ticket was created
     */
	public int stopCountDown() {
		stopwatch.stopTimer();
		return stopwatch.elapsed;
	}
	

    /**
     * Returns the customer associated with this ticket.
     *
     * @return the customer who placed the order
     */
	public Customer getCustomer() {
		return this.thisCustomer;
	}
	
	/**
     * Returns the list of toppings included in this ticket.
     *
     * @return the toppings list
     */
	public ArrayList<Toppings> getToppingsList() {
		return this.ingredients;
	}
	
	/**
     * Returns the number of toppings currently on this order.
     *
     * @return the size of the toppings list
     */
	public int getOrderSize() {
		return this.ingredients.size();
	}
	
	/**
     * Adds a new topping to the ticket's ingredients list.
     *
     * @param newIngredient the topping to add
     */
	public void addTopping(Toppings newIngredient) {
		this.ingredients.add(newIngredient);
	}

}