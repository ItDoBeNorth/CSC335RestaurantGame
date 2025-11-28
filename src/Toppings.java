
public abstract class Toppings {
	
	private double price;
	private String toppingName;
	
	public Toppings(double price,String toppingName) {
		// price and name 
		this.toppingName = toppingName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	
	
	public String getToppingName() {
		return this.toppingName;
	}
	
	//DELETE, prolly dont need
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//            return true;
//        }
//		if (obj == null || this.getClass() != obj.getClass()) {
//            return false;
//        }
//		Toppings other = (Toppings) obj;
//		return (other.getToppingName() == this.getToppingName());
//	}

}
