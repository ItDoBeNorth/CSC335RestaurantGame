package RestaurantGame;

public class Onion extends Toppings {
	
	/**
	 * Constructor that creates an Onion topping with a specified price and name.
	 * @param price: The price of the topping
	 * @param toppingName: Name of the topping
	 */
	public Onion(double price, String toppingName) {
		super(price, toppingName);
	}
	
	/**
	 * Constructor that creates a default Onion topping with the preset price and name.
	 */
	public Onion() {
		super(.80, "onion");
	}

}
