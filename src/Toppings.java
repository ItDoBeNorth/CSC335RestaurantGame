
public abstract class Toppings {

	private double price;
	private String toppingName;

	public Toppings(double price, String toppingName) {
		this.price = price;
		this.toppingName = toppingName;
	}

	public double getPrice() {
		return this.price;
	}

	public String getToppingName() {
		return this.toppingName;
	}

	public boolean isTheSameIngredient(Toppings obj) {
		if (obj != null && this.getClass() == obj.getClass() && obj.getToppingName() == this.getToppingName()) {
			return true;
		}
		return false;
	}

}
