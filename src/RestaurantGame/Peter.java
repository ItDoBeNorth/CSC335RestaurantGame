package RestaurantGame;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Peter extends KnownCustomer {
	
	private Toppings favoriteTopping=new Lettuce();
	
	/**
	 * Constructor that creates a Peter customer with predefined attributes.   
	 */
	public Peter() {

		super("Peter", "square", Color.GREENYELLOW, new ArrayList<Toppings>() {
			{
				add(new Patty());
				add(new Lettuce());
			}
		}, Personality.URGENT, 2);
	}
	
	 /**
     * Returns Peter's full order, which consists of a preset of favorite order
     * and a repeated instances of favorite topping until the maximum
     * number picks.
     *
     * @param ingredientsList: The full list of available toppings.
     * @param maxPick: The maximum number of ingredients Peter is allowed to choose.
     * @return: A list of Toppings representing Peter's order
     */
	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order = new ArrayList<Toppings>(super.getFavoriteOrder());
		for (int i = order.size(); i < maxPick; i++) {
			order.add(favoriteTopping);
		}
		return order;
	}

}
