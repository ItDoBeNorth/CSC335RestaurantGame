import java.util.ArrayList;

public abstract class KnownCustomer implements Customer {
	protected String name;
	protected String shape;
	protected String color;
	protected final ArrayList<Toppings> favoriteOrder;
	
	public KnownCustomer(String name,String shape, String color, ArrayList<Toppings> favoriteOrder) {
		this.name=name;
		this.shape=shape;
		this.color=color;
		this.favoriteOrder=favoriteOrder;
	}

	public String getName() {
		return name;
	}
	public String getShape() {
		return shape;
	}
	public String getColor() {
		return color;
	}
	public ArrayList<Toppings> getFavoriteOrder(){
		return favoriteOrder;
	}
}
