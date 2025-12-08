import java.util.ArrayList;

import javafx.scene.paint.Color;

public class John extends KnownCustomer {
	
	private Toppings favoriteTopping=new Cheese();
	
	/**
	 * Constructor that creates a John customer with predefined attributes.   
	 */
	public John() {
		super("John","triangle",Color.ORANGE,new ArrayList<Toppings>(){{add (new Patty());add(new Cheese());}},Personality.GENEROUS, 3);
	}
	
	 /**
     * Returns John's full order, which consists of a preset of favorite order
     * and a repeated instances of favorite topping until the maximum
     * number picks.
     *
     * @param ingredientsList: The full list of available toppings.
     * @param maxPick: The maximum number of ingredients John is allowed to choose.
     * @return: A list of Toppings representing John's order
     */
	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order= new ArrayList<Toppings>(super.getFavoriteOrder());
		for (int i=order.size();i<maxPick;i++) {
				order.add(favoriteTopping);
			}
		return order;
	}

}
