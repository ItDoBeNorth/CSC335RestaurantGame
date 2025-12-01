import java.util.ArrayList;

public class Mariah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Onion();
	
	public Mariah() {

		super("Mariah","triangle","green",new ArrayList<Toppings>(){{add(new Pickle());}},Personality.ACCURATE, 5);
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
