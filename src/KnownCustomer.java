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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
		GenericCustomer other = (GenericCustomer) obj;
		return (other.getName() == this.getName());
	}
}
