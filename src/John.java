import java.util.ArrayList;

import javafx.scene.paint.Color;

public class John extends KnownCustomer {
	
	private Toppings favoriteTopping=new Cheese();
	
	public John() {
		super("John","triangle",Color.ORANGE,new ArrayList<Toppings>(){{add(new Cheese());}},Personality.GENEROUS, 3);
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
