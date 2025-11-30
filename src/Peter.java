import java.util.ArrayList;

public class Peter extends KnownCustomer {
	
	private Toppings favoriteTopping=new Lettuce();
	
	public Peter() {
		super("Peter","circle","yellow",new ArrayList<Toppings>(){{add(new Lettuce());}},Personality.URGENT);
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
