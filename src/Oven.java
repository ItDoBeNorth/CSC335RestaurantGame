import java.util.ArrayList;
import java.util.List;

/**
 * A generic Oven class that stores a limited number of ingredients of type T.
 * The oven behaves like a container with a maximum capacity, allowing items to
 * be added, removed, listed, or cleared.
 *
 * @param <T> the type of ingredient stored in the oven
 */
public class Oven<T> {
	private List<T> list;
	private int size;
	public int limit;
	
	/**
     * Creates a new Oven with a specified capacity.
     *
     * @param limit: The maximum number of ingredients allowed in the oven.
     */
	public Oven(int limit) {
		list = new ArrayList<T>();
		size = 0;
		this.limit = limit;
	}
	
	 /**
     * Trys to add an ingredient to the oven.
     *
     * @param ingredient the item to be added
     * @return true if the ingredient was successfully added,
     * false if the oven is already full
     */
	public boolean addIngredient(T indredient) {
		if (size < limit) {
			list.add(indredient);
			size++;
			return true;
		}
		return false;
	}
	
	/**
     * Removes all items from the oven and resets the size.
     */
	public void clearOven() {
		list.clear();
		size = 0;
	}
	
	 /**
     * Prints the oven's contents to the console.
     */
	public void printList() {
		System.out.println(list);
	}
	
	/**
     * Returns the current number of ingredients inside the oven.
     *
     * @return: The number of items stored
     */
	public int getSize() {
		return size;
	}
	
	/**
     * Returns the full list of ingredients in the oven.
     *
     * @return: An ArrayList containing all items inside the oven
     */
	public ArrayList<T> getList(){
		return (ArrayList<T>) list;
	}
	
	/**
    * Removes a specific ingredient from the oven if it exists.
    *
    * @param topping: The item to remove
    * @return: The removed ingredient, null if it was not found.
    */
	public Toppings remove(Toppings topping) {
		int i = list.indexOf(topping);
		Toppings removedPatty = (Toppings) list.remove(i);
		size--;
		return removedPatty;
	}

}
