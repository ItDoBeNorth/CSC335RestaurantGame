import java.util.ArrayList;

public class David extends KnownCustomer {
	
	private Toppings favoriteTopping=new Pickle();
	
	public David() {
		super("David","triangle","orange",new ArrayList<Toppings>(){{add(new Onion());}},Personality.URGENT, 1);
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
