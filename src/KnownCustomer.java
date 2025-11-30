import java.util.ArrayList;

public abstract class KnownCustomer implements Customer {
	protected String name;
	protected String shape;
	protected String color;
	protected final ArrayList<Toppings> favoriteOrder;
	public enum Personality {ACCURATE, URGENT, GENEROUS;}
	private Personality personality;
	public KnownCustomer(String name,String shape, String color, ArrayList<Toppings> favoriteOrder,Personality personality) {
		this.name=name;
		this.shape=shape;
		this.color=color;
		this.favoriteOrder=favoriteOrder;
		this.personality=personality;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getShape() {
		return shape;
	}
	@Override
	public String getColor() {
		return color;
	}
	public ArrayList<Toppings> getFavoriteOrder(){
		return favoriteOrder;
	}
	public Personality getPersonality() {
		return personality;
	}
	
}
