import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Mariah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Onion();
	
	public Mariah() {

		super("Mariah","circle",Color.LIGHTCYAN,new ArrayList<Toppings>(){{add (new Patty());add(new Pickle());}},Personality.ACCURATE, 5);
	}

	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order= new ArrayList<Toppings>(super.getFavoriteOrder());
		for (int i=order.size();i<maxPick;i++) {
				order.add(favoriteTopping);
			}
		return order;
	}

}
