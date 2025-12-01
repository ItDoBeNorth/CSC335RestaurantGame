
public abstract class CookableToppings extends Toppings{
	
	private double prepTime;
	private Countdown timer;
	
	public CookableToppings(double price, double prepTime, String toppingName) {
		super(price, toppingName);
		this.prepTime = prepTime;
	}
	public double getPrepTime() {
		return this.prepTime;
	}
	//can be moved to something else like cooking sandwhich instead
	public boolean startCooking() {
		return timer.startCountdown(prepTime);
	}
	
	public double stopCooking() {
		return timer.timeLeft;
	}

}
