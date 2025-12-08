/**
 * FoodItem is an abstract base class representing any item in the game
 * that has a price and a preparation time.
 */
public abstract class FoodItem {
	private double price;
	private double prepTime;

   /**
    * creates a FoodItem with the given price and preparation time.
    *
    * @param price: The cost of the item
    * @param prepTime: The time needed to prepare the item
    */
	public FoodItem(double price, double prepTime) {
		this.price = price;
		this.prepTime = prepTime;
		
	}
	
	/**
     * Returns the price of this food item.
     *
     * @return the item's price
     */
	public double getPrice() {
		return this.price;
	}
	
	/**
     * Returns the required preparation time for this food item.
     *
     * @return preparation time in seconds
     */
	public double getPrepTime() {
		return this.prepTime;
	}
	
}
