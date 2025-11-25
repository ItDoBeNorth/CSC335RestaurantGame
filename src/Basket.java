import java.util.*;

public class Basket<T> {
	private List<T> list;
	private int size;
	private int limit;
	
	public Basket(int limit) {
		list = new ArrayList<T>();
		size = 0;
		this.limit = limit;
	}
	
	public void addIngredient(T indredient) {
		if (size != limit) {
			list.add(indredient);
			size++;
		}	
	}
	
	public void clearBasket() {
		list.clear();
		size = 0;
	}
	
	public void printList() {
		System.out.println(list);
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<T> getList(){
		return (ArrayList<T>) list;
	}
	
}
