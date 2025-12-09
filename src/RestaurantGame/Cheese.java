package RestaurantGame;

public class Cheese extends Toppings {
	
	/**
	 * Constructor that creates an Cheese topping with a specified price and name.
	 * @param price: The price of the topping
	 * @param toppingName: Name of the topping
	 */
	public Cheese(double price, String toppingName) {
		super(price, toppingName);

	}
	
	/**
	 * Constructor that creates a default Cheese topping with the preset price and name.
	 */
	public Cheese() {
		super(.50, "cheese");
	}

}
