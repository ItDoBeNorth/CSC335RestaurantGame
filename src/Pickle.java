
public class Pickle extends Toppings {
	
	/**
	 * Constructor that creates an Pickle topping with a specified price and name.
	 * @param price: The price of the topping
	 * @param toppingName: Name of the topping
	 */
	public Pickle(double price, String toppingName) {
		super(price, toppingName);
		
	}
	
	/**
	 * Constructor that creates a default Onion topping with the preset price and name.
	 */
	public Pickle() {
		super(1.00, "pickle");
	}
	
	

}
