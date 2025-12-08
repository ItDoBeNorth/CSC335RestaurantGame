
/**
 * An abstract class for toppings that can be cooked.
 * Cookable toppings track how long they have been cooking using
 * an timer.
 */
public abstract class CookableToppings extends Toppings{
	
	private double prepTime;
	private Countdown timer;
	private double cookingTime;
	
	 /**
      * creates a cookable topping with a price, prep time,
      * and name.
      *
      * @param price: The cost of this topping
      * @param prepTime: how long it needs to cook
      * @param toppingName: The name of the topping
      */
	public CookableToppings(double price, double prepTime, String toppingName) {
		super(price, toppingName);
		this.prepTime = prepTime;
		timer=new Countdown();
	}
	
	 /**
      * Returns the recommended preparation (cooking) time for this topping.
      *
      * @return the ideal cooking time in seconds
      */
	public double getPrepTime() {
		return this.prepTime;
	}
	
	//can be moved to something else like cooking sandwhich instead
	/**
     * Starts the cooking timer for this topping.
     */
	public void startCooking() {
		timer.startStopwatch();;
	}
	
	 /**
      * Returns how long this topping has been cooking so far.
      *
      * @return the number of seconds elapsed on the timer
      */
	public double getCookingTime() {
		cookingTime=timer.elapsed;
		return cookingTime;
	}
	
	/**
     * Stops the cooking timer.
     */
	public void stopCooking() {
		timer.stopTimer();
		
	}

}
