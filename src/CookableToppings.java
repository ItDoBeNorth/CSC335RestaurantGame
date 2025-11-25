
public abstract class CookableToppings extends Toppings{
	
	private double prepTime;
	
	public CookableToppings(double price, double prepTime, String toppingName) {
		super(price, toppingName);
		this.prepTime = prepTime;
	}
	public double getPrepTime() {
		return this.prepTime;
	}
	

}
