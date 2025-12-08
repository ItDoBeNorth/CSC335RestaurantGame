
public abstract class CookableToppings extends Toppings {

	private double prepTime;
	private Countdown timer;
	private double cookingTime;

	public CookableToppings(double price, double prepTime, String toppingName) {
		super(price, toppingName);
		this.prepTime = prepTime;
		timer = new Countdown();
	}

	public double getPrepTime() {
		return this.prepTime;
	}

	// can be moved to something else like cooking sandwhich instead
	public void startCooking() {
		timer.startStopwatch();
		;
	}

	public double getCookingTime() {
		cookingTime = timer.elapsed;
		return cookingTime;
	}

	public void stopCooking() {
		timer.stopTimer();

	}

}
