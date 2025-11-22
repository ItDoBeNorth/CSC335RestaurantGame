import java.util.ArrayList;

public interface Customer {
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList,int maxPick);
}
