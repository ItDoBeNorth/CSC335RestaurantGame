import java.util.*;

public class Burger {
	private Stack<Toppings> toppingList;

	public Burger() {
		toppingList = new Stack<>();
	}

	public Burger(Burger burger) {
		toppingList = new Stack<>();
		toppingList.addAll(burger.getToppings());
	}

	public void addTopping(Toppings topping) {
		toppingList.push(topping);
	}

	public Toppings RemoveLastTopping() {
		if (!toppingList.isEmpty()) {
			return toppingList.pop();
		}
		return null;

	}

	public void reset() {
		toppingList.clear();
	}

	public ArrayList<Toppings> getToppings() {
		return new ArrayList<Toppings>(toppingList);
	}

}
