
public class Patty extends CookableToppings {

	public Patty(double price, double prepTime, String toppingName) {
		super(price, prepTime, toppingName);
	}
	
	public Patty() {
		super(1.00, .10, "onion");
	}

}
