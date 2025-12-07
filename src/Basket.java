import java.util.*;

public class Basket<T> {
	private List<T> list;
	private int size;
	public int limit;
	
	public Basket(int limit) {
		list = new ArrayList<T>();
		size = 0;
		this.limit = limit;
	}
	
	public boolean addIngredient(T ingredient) {
		if (size < limit) {
			list.add(ingredient);
			size++;
			return true;
		}	
		return false;
	}
	
	public void clearBasket() {
		list.clear();
		size = 0;
	}
	
	public void printList() {
		System.out.println(list);
	}
	
	public void remove(Toppings topping) {
		list.remove(topping);
		size --;
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<T> getList(){
		return (ArrayList<T>) list;
	}
	
}
