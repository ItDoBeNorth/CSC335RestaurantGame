import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Mariah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Onion();
	
	/**
	 * Constructor that creates a Mariah customer with predefined attributes.   
	 */
	public Mariah() {

		super("Mariah", "circle", Color.LIGHTCYAN, new ArrayList<Toppings>() {
			{
				add(new Patty());
				add(new Pickle());
			}
		}, Personality.ACCURATE, 5);
	}
	
	 /**
     * Returns Mariah's full order, which consists of a preset of favorite order
     * and a repeated instances of favorite topping until the maximum
     * number picks.
     *
     * @param ingredientsList: The full list of available toppings.
     * @param maxPick: The maximum number of ingredients Mariah is allowed to choose.
     * @return: A list of Toppings representing Mariah's order
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
