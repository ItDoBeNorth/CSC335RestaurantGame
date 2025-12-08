
public abstract class FoodItem {
	private double price;
	private double prepTime;

	public FoodItem(double price, double prepTime) {
		this.price = price;
		this.prepTime = prepTime;

	}

	public double getPrice() {
		return this.price;
	}

	public double getPrepTime() {
		return this.prepTime;
	}

}
