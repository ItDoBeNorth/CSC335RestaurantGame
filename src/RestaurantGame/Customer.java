package RestaurantGame;
import java.util.ArrayList;

import javafx.scene.paint.Color;
/**
 * Customer defines the required behaviors for all customer types
 * in the game. Both GenericCustomers and KnownCustomers must 
 * implement these methods.
 *
 */
public interface Customer {
	/**
     * creates the customer's order based on a list of available ingredients
     * and the maximum number of items the customer is allowed to pick.
     *
     * @param ingredientsList: The list of all possible toppings
     * @param maxPick: The maximum number of toppings this customer may choose
     * @return a list of toppings that make up the customer's order
     */
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList,int maxPick);
	 /**
      * Returns the customer's name.
      *
      * @return the customer's name string
      */
	public String getName();
    /**
     * Returns the customer's display color.
     *
     * @return a Color object
     */
	public Color getColor();
	 /**
      * Returns shape of the customer.
      *
      * @return the shape name as a string
      */
	public String getShape();
	 /**
      * Returns how long this customer will wait before leaving.
      *
      * @return patience level in seconds
      */
	public int patienceLevel();
	 /**
      * Returns how much time is left on the customer's countdown timer.
      *
      * @return time remaining in seconds
      */
	public double timeleft();
	 /**
      * Starts the customer's countdown timer.
      *
      * @param time the number of seconds to count down from
      */
	public void startTimer(double time);
	/**
     * Stops the customer's countdown timer.
     */
	public void stopTimer();
	/**
     * Returns whether the customer's patience countdown timer
     * is currently active.
     *
     * @return true if the countdown is running; false otherwise
     */
	public boolean CDisRunning();
}
