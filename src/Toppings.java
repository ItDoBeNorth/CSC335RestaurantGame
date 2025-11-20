
public abstract class Toppings {
	
	private double price;
	private double prepTime;
	private String toppingName;
	
	public Toppings(double price, double prepTime, String toppingName) {
		this.price = price;
		this.prepTime = prepTime;
		this.toppingName = toppingName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public double getPrepTime() {
		return this.prepTime;
	}
	
	public String getToppingName() {
		return this.toppingName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
		Toppings other = (Toppings) obj;
		return (other.getToppingName() == this.getToppingName());
	}

}
