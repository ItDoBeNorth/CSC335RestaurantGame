package RestaurantGame;

public class Tomato extends Toppings {
	
	
	/**
	 * Constructor that creates an Tomato topping with a specified price and name.
	 * @param price: The price of the topping
	 * @param toppingName: Name of the topping
	 */
	public Tomato(double price,  String toppingName) {
		super(price, toppingName);

	}
	
	/**
	 * Constructor that creates a default Tomato topping with the preset price and name.
	 */
	public Tomato() {
		super(.75, "tomato");
	}

}
