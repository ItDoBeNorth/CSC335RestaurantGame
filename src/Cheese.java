
public class Cheese extends Toppings {
	private String cheese;

	public Cheese(double price, double prepTime) {
		super(price, prepTime);
		cheese = "cheese";
		
	}
	
	public String getName() {
		return cheese;
	}

}
