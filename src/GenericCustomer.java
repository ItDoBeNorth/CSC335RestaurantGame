import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * A GenericCustomer is a customer with randomly assigned attributes such as
 * shape, color, and name. The customer will also generate a random order
 * consisting of randomly selected toppings up to maxPick.
 *
 * The class also includes a patience timer controlling how long the customer
 * will wait before leaving.
 */
public class GenericCustomer implements Customer{
	private static Random r =new Random();
	private String[] shapes={"circle"};
	private Color[] colors= {Color.PINK,Color.PURPLE,Color.BLUEVIOLET,Color.DARKBLUE};
	private String[] names= {"Madison","Henry","Dany","Al"}; //will add more 
	private String shape;
	private Color color;
	private String name;
	// mult by day to get time patience + 10 sec
	private int patienceLevel;
	private Countdown countdown;
	
	 /**
     * Creates a GenericCustomer with random shape, color, name, and patience level.
     */
	public GenericCustomer() {
		// customer shape and color (for GUI Implementation)
		Random r = new Random();
		shape = shapes[r.nextInt(shapes.length)];
		color = colors[r.nextInt(colors.length)];
		name = names[r.nextInt(names.length)];
		patienceLevel = r.nextInt(4, 7);
		this.countdown = new Countdown();

	}
	
	 /**
     * Generates a random order for the customer by selecting random toppings
     * from the available ingredient list until maxPick.
     *
     * @param ingredientsList: List of all possible toppings.
     * @param maxPick: Maximum number of toppings this customer may choose.
     * @return: A list of randomly selected Toppings for the customer's order.
     */
	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order = new ArrayList<>();
		Patty newPatty = new Patty();
		order.add(newPatty);
		for (int i = 0; i < maxPick; i++) {
			Toppings currTopping = ingredientsList.get(r.nextInt(ingredientsList.size()));
			order.add(currTopping);
		}
		return order;
	}
	
	 /**
     * Returns the customer's name.
     *
     * @return: The customer's name
     */
	@Override
	public String getName() {
		return name;
	}
	
	 /**
     * Gets the customer's shape.
     *
     * @return: The shape name as a string
     */
	@Override
	public String getShape() {
		return shape;
	}
	
	 /**
     * Returns the customer's display color.
     *
     * @return A Color object
     */
	@Override
	public Color getColor() {
		return color;
	}
	
	 /**
     * Returns the patience level which is how long this customer will wait.
     *
     * @return: Patience time in seconds.
     */
	@Override
	public int patienceLevel() {
		return patienceLevel;
	}
	
	/**
     * Returns how much time is left on the customer's countdown timer.
     *
     * @return remaining time in seconds
     */
	@Override
	public double timeleft() {
		// TODO Auto-generated method stub
		return countdown.timeLeft;
	}
	
	/**
     * Starts the patience countdown timer for this customer.
     *
     * @param time the amount of time to count down from
     */
	@Override
	public void startTimer(double time) {
		countdown.startCountdown(time);

	}
	
	/**
     * Stops the customer's countdown timer.
     */
	@Override
	public void stopTimer() {
		countdown.stopTimer();

	}
	
	/**
     * Returns whether the customer's countdown is currently running.
     *
     * @return true if the timer is active, false otherwise
     */
	@Override
	public boolean CDisRunning() {
		return countdown.countDownIsRunning;
	}
}