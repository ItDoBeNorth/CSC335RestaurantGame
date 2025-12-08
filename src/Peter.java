import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Peter extends KnownCustomer {

	private Toppings favoriteTopping = new Lettuce();

	public Peter() {

		super("Peter", "square", Color.GREENYELLOW, new ArrayList<Toppings>() {
			{
				add(new Patty());
				add(new Lettuce());
			}
		}, Personality.URGENT, 2);
	}

	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order = new ArrayList<Toppings>(super.getFavoriteOrder());
		for (int i = order.size(); i < maxPick; i++) {
			order.add(favoriteTopping);
		}
		return order;
	}

}
