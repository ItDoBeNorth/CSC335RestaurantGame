import java.util.ArrayList;

public interface Customer {
	public ArrayList<Toppings> getOrder(Toppings[] ingredientsList,int maxPick);
}
