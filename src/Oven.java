import java.util.ArrayList;
import java.util.List;

public class Oven<T> {
	private List<T> list;
	private int size;
	public int limit;
	
	public Oven(int limit) {
		list = new ArrayList<T>();
		size = 0;
		this.limit = limit; 
	}
	
	public boolean addIngredient(T indredient) {
		if (size < limit) {
			list.add(indredient);
			size++;
			return true;
		}	
		return false;
	}
	
	public void clearOven() {
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

	public Toppings remove(Toppings topping) {
		int i=list.indexOf(topping);
		Toppings removedPatty=(Toppings)list.remove(i);
		size --;
		return removedPatty;
	}
	
}
