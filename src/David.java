import java.util.ArrayList;

public class David extends KnownCustomer {
	
	private Toppings favoriteTopping=new Onion();
	
	public David() {
		super("David","triangle","orange",new ArrayList<Toppings>(){{add(new Onion());}});
		//super.setPersonality(Personality.URGERNT);
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
