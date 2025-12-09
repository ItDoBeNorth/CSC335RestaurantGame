import java.util.*;

/**
 * The Burger class represents a stack of toppings.
 * New toppings are added on top of the stack, and the most recent
 * topping can be removed.
 */

public class Burger{
	private Stack<Toppings> toppingList;
	
	/**
     * Creates an empty Burger with no toppings.
     */
	public Burger() {
		toppingList = new Stack<>();
	}
	
	/**
     * Copy constructor.
     * Creates a new Burger that is a copy of another burger's toppings.
     *
     * @param burger: the Burger to copy
     */
	public Burger(Burger burger) {
		toppingList = new Stack<>();
		toppingList.addAll(burger.getToppings());
	}
	
	/**
     * Adds a topping onto the burger.
     * This places the topping on top of the stack.
     *
     * @param topping the topping to add
     */
	public void addTopping(Toppings topping) {
		toppingList.push(topping);
	}
	
	/**
     * Removes the most recently added topping from the burger.
     *
     * @return the topping removed, or null if the burger is empty
     */
	public Toppings RemoveLastTopping() {
		if (!toppingList.isEmpty()) {
			return toppingList.pop();
		}
		return null;

	}
	
	/**
     * Removes all toppings from the burger, leaving it empty.
     */
	public void reset() {
		toppingList.clear();
	}
	
	/**
     * Returns a list containing all toppings in the burger
     * in the order they were stacked.
     *
     * @return an ArrayList copy of the topping stack
     */
	public ArrayList<Toppings> getToppings() {
		return new ArrayList<Toppings>(toppingList);
	}

}
