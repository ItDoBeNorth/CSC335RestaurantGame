
public class Lettuce extends Toppings {
	
	/**
	 * Constructor that creates an Lettuce topping with a specified price and name.
	 * @param price: The price of the topping
	 * @param toppingName: Name of the topping
	 */
	public Lettuce(double price, String toppingName) {
		super(price, toppingName);
	}
	
	/**
	 * Constructor that creates a default Lettuce topping with the preset price and name.
	 */
	public Lettuce() {
		super(.25, "lettuce");
	}

}
