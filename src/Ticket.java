import java.util.ArrayList;

public class Ticket {
	private ArrayList<Toppings> ingredients;
	private Customer thisCustomer;
	
	public Ticket(Customer thisCustomer, ArrayList<Toppings> ingredients) {
		this.thisCustomer = thisCustomer;
		this.ingredients = ingredients;
	}
	
	public Customer getCustomer() {
		return this.thisCustomer;
	}
	
	public ArrayList<Toppings> getToppingsList() {
		return this.ingredients;
	}
	
	public int getOrderSize() {
		return this.ingredients.size();
	}
	
	public void addTopping(Toppings newIngredient) {
		this.ingredients.add(newIngredient);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
		Ticket other = (Ticket) obj;
		return (other.getToppingsList() == this.getToppingsList() && other.getCustomer() == this.getCustomer());
	}

}
