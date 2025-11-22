import java.util.ArrayList;
import java.util.Random;

public class GenericCustomer implements Customer{
	private static Random r =new Random();
	private String[] shapes={"circle","square","triangle"};
	private String[] colors= {"blue","green","red","yellow","orange"};
	private String[] names= {"Sarah","Peter","David","Al"}; //will add more 
	private String shape;
	private String color;
	private String name;
	
	public GenericCustomer() {
		//customer shape and color (for GUI Implementation)
		Random r=new Random();
		shape=shapes[r.nextInt(shapes.length)];
		color=colors[r.nextInt(colors.length)];
		name=names[r.nextInt(names.length)];
	}
	
	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order=new ArrayList<>();
		
		for (int i=0;i<maxPick;i++) {
			Toppings currTopping=ingredientsList.get(r.nextInt(ingredientsList.size()));
			order.add(currTopping);
		}
		return order;
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
