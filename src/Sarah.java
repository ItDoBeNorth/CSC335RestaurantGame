import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Sarah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Tomato();
	
	public Sarah() {
		super("Sarah","triangle",Color.RED,new ArrayList<Toppings>(){{add(new Tomato());}},Personality.PATIENT, 20);
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
