import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * An abstract base class for special customers who have known to the restaurant,
 * predefined attributes such as name, shape, color, personality,
 * and a favorite order.
 *
 */
public abstract class KnownCustomer implements Customer {
	protected String name;
	protected String shape;
	protected Color color;
	
	protected final ArrayList<Toppings> favoriteOrder;
	public enum Personality {ACCURATE, URGENT, GENEROUS, PATIENT;}
	private Personality personality;
	// mult by day to get time patience + 10 sec
	private int patienceLevel;
	private Countdown countdown;
	
	 /**
     * Creates a KnownCustomer with explicit attributes.
     *
     * @param name: The customer's name
     * @param shape: The shape of the customer
     * @param color: The customer's display color
     * @param favoriteOrder: The customer's favorite toppings list
     * @param personality: he customer's personality behavior type
     * @param patienceLevel: How long the customer will wait before leaving
     */
	public KnownCustomer(String name,String shape, Color color, ArrayList<Toppings> favoriteOrder,Personality personality, int patienceLevel) {
		this.name=name;
		this.shape=shape;
		this.color=color;
		this.favoriteOrder=favoriteOrder;
		this.personality=personality;
		this.patienceLevel = patienceLevel;
		this.countdown = new Countdown();
	}
	
	 /**
     * Returns the customer's name.
     *
     * @return The name of the customer
     */
	@Override
	public String getName() {
		return name;
	}
	
	/**
     * Returns the customer's shape.
     *
     * @return the shape name as a string
     */
	@Override
	public String getShape() {
		return shape;
	}
	
	 /**
     * Returns the customer's color.
     *
     * @return a Color object
     */
	@Override
	public Color getColor() {
		return color;
	}
	
	 /**
     * Returns this customer's favorite order.
     *
     * @return the list of toppings that form this customer's order
     */
	public ArrayList<Toppings> getFavoriteOrder(){
		return favoriteOrder;
	}
	
	 /**
     * Returns the personality type of the customer.
     *
     * @return the customer's Personality
     */
	public Personality getPersonality() {
		return personality;
	}
	
	/**
     * Returns the amount of time in seconds that the customer will wait.
     *
     * @return patience time in seconds
     */
	@Override
	public int patienceLevel() {
		return patienceLevel;
	}
	
	/**
     * Returns how much time remains on this customer's countdown timer.
     *
     * @return seconds remaining
     */
	@Override
	public double timeleft() {
		return countdown.timeLeft;
	}
	
	/**
     * Starts the customer's patience countdown.
     *
     * @param time the starting time in seconds
     */
	@Override
	public void startTimer(double time) {
		countdown.startCountdown(time);
		
	}
	
	 /**
     * Stops the customer's patience countdown.
     */
	@Override
	public void stopTimer() {
		countdown.stopTimer();
		
	}
	
	 /**
     * Returns whether the patience timer is currently active.
     *
     * @return true if timer is running, false otherwise
     */
	@Override
	public boolean CDisRunning() {
		return countdown.countDownIsRunning;
	}
	
}
