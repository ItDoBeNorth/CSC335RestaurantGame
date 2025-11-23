import java.util.*;


public class Burger extends FoodItem{
	private Stack<Toppings> toppingList;
	

	public Burger(double price, double prepTime) {
		super(price, prepTime);
		toppingList = new Stack<>();
	}
	
	public void addTopping(Toppings topping) {
		toppingList.push(topping);
	}
	
	public void RemoveLastTopping() {
		if (!toppingList.isEmpty()) {
			toppingList.pop();
		}
		
	}
	
	public void reset() {
		toppingList.clear();
	}
	
	public void printStack() {
		System.out.println(toppingList);
	}
	
	public ArrayList<Toppings> getToppings() {
		return new ArrayList<Toppings>(toppingList);
	}
	
	

}
