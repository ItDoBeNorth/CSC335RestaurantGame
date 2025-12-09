import java.util.*;

/**
 * A Basket is a generic container that stores ingredients of type T
 * up to a specified limit. It supports adding, removing, clearing,
 * printing, and retrieving items.
 *
 * @param <T> the type of elements stored inside the basket
 */
public class Basket<T> {
	private List<T> list;
	private int size;
	public int limit;
	
	/**
     * Creates a Basket with a specified capacity limit.
     *
     * @param limit: the maximum number of items the basket can hold
     */
	public Basket(int limit) {
		list = new ArrayList<T>();
		size = 0;
		this.limit = limit;
	}
	
	/**
     * Attempts to add an ingredient to the basket.
     *
     * @param ingredient: the item to add
     * @return true if the item was added successfully,
     * false if the basket is already full
     */
	public boolean addIngredient(T ingredient) {
		if (size < limit) {
			list.add(ingredient);
			size++;
			return true;
		}
		return false;
	}
	
	/**
     * Removes all items from the basket and resets the size.
     */
	public void clearBasket() {
		list.clear();
		size = 0;
	}
	
	/**
     * Prints the basket's contents to the console.
     */
	public void printList() {
		System.out.println(list);
	}
	
    /**
     * Removes the specified ingredient from the basket.
     *
     * @param topping: the topping to remove from the basket
     */
	public void remove(Toppings topping) {
		list.remove(topping);
		size--;
	}
	
	/**
	 * Returns the current number of items inside the basket.
     *
     * @return the count of stored items
	 */
	public int getSize() {
		return size;
	}
	
	 /**
      * Returns the list of ingredients as an ArrayList.
      *
      * @return the list of items in the basket
      */
	public ArrayList<T> getList(){
		return (ArrayList<T>) list;
	}

}
