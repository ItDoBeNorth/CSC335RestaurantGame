import java.util.ArrayList;

public class Mariah extends KnownCustomer {
	
	private Toppings favoriteTopping=new Pickle();
	
	public Mariah() {
		super("Mariah","triangle","green",new ArrayList<Toppings>(){{add(new Pickle());}});
		super.setPersonality(Personality.GENEROUS);
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
