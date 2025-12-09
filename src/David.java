import java.util.ArrayList;

import javafx.scene.paint.Color;

public class David extends KnownCustomer {
	
	private Toppings favoriteTopping=new Pickle();
	
	/**
	 * Constructor that creates a David customer with predefined attributes.   
	 */
	public David() {
		super("David", "square", Color.DARKGREEN, new ArrayList<Toppings>() {
			{
				add(new Patty());
				add(new Onion());
			}
		}, Personality.URGENT, 1);
	}
	
	/**
     * Returns David's full order, which consists of a preset of favorite order
     * and a repeated instances of favorite topping until the maximum
     * number picks.
     *
     * @param ingredientsList: The full list of available toppings.
     * @param maxPick: The maximum number of ingredients David is allowed to choose.
     * @return: A list of Toppings representing David's order
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
