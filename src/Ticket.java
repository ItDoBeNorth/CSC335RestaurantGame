import java.util.ArrayList;

public class Ticket {
	private ArrayList<Toppings> ingredients;
	private Customer thisCustomer;
	private Countdown stopwatch;

	public Ticket(Customer thisCustomer, ArrayList<Toppings> ingredients) {
		this.thisCustomer = thisCustomer;
		this.ingredients = ingredients;
		this.stopwatch = new Countdown();
		stopwatch.startStopwatch();
	}

	public int stopCountDown() {
		stopwatch.stopTimer();
		return stopwatch.elapsed;
	}

	public Customer getCustomer() {
		return this.thisCustomer;
	}

	public ArrayList<Toppings> getToppingsList() {
		return this.ingredients;
	}

	public int getOrderSize() {
		return this.ingredients.size();
	}

	public void addTopping(Toppings newIngredient) {
		this.ingredients.add(newIngredient);
	}

}