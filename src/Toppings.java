
public abstract class Toppings {
	
	private double price;
	private double prepTime;
	private String toppingName;
	
	public Toppings(double price, double prepTime, String toppingName) {
		this.price = price;
		this.prepTime = prepTime;
		this.toppingName = toppingName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getPrepTime() {
		return this.prepTime;
	}
	
	public String getToppingName() {
		return this.toppingName;
	}

}
