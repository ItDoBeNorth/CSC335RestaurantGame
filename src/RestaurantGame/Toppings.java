package RestaurantGame;
	
/**
 * An abstract base class representing a topping ingredient.
 * Each topping has a price and a name and can be compared to 
 * other toppings to check if they represent the same ingredient.
 */
public abstract class Toppings {

	private double price;
	private String toppingName;
	
	/**
     * Constructor that creates a topping with the given price and name.
     *
     * @param price: The cost of the topping
     * @param toppingName: The display name of the topping
     */
	public Toppings(double price,String toppingName) {
		this.price = price;
		this.toppingName = toppingName;
	}
	
	 /**
     * Returns the price of this topping.
     *
     * @return the topping's price
     */
	public double getPrice() {
		return this.price;
	}
	
	
	 /**
     * Returns the name of this topping.
     *
     * @return the topping's name
     */
	public String getToppingName() {
		return this.toppingName;
	}
	
	/**
     * Determines whether another topping represents the same ingredient.
     * @param obj the other topping to compare to
     * @return true if both toppings are the same ingredient, false otherwise
     */
	public boolean isTheSameIngredient(Toppings obj) {
		if (obj != null && this.getClass() == obj.getClass() && obj.getToppingName() == this.getToppingName()) {
			return true;
		}
		return false;
	}

}
