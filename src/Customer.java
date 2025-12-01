import java.util.ArrayList;

public interface Customer {
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList,int maxPick);
	public String getName();
	public String getColor();
	public String getShape();
	public int patienceLevel();
}
