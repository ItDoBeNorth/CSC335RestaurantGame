import java.util.ArrayList;

import javafx.scene.paint.Color;

public interface Customer {
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList,int maxPick);
	public String getName();
	public Color getColor();
	public String getShape();
	public int patienceLevel();
}
