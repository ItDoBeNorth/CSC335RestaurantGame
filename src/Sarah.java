import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Sarah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Tomato();
	
	/**
	 * Constructor that creates a Sarah customer with predefined attributes.   
	 */
	public Sarah() {
		super("Sarah", "triangle", Color.RED, new ArrayList<Toppings>() {
			{
				add(new Patty());
				add(new Tomato());
			}
		}, Personality.PATIENT, 15);
	}
	
	 /**
     * Returns Sarah's full order, which consists of a preset of favorite order
     * and a repeated instances of favorite topping until the maximum
     * number picks.
     *
     * @param ingredientsList: The full list of available toppings.
     * @param maxPick: The maximum number of ingredients Sarah is allowed to choose.
     * @return: A list of Toppings representing Sarah's order
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
