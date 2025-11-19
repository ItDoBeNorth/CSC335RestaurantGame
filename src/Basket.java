import java.util.*;

public class Basket<T> {
	private List<T> list;
	private int size;
	
	public Basket() {
		list = new ArrayList<T>();
		size = 0;
	}
	
	public void addIngredient(T indredient) {
		list.add(indredient);
		size++;
	}
	
	public void clearBasket() {
		list.clear();
	}
	
	public void printList() {
		System.out.println(list);
	}
	
	
}
