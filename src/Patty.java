public class Patty extends CookableToppings {
	public enum CookingState {UNDERCOOKED,COOKED,OVERCOOKED}
	private CookingState currState;
	private String stateImage;
	public Patty(double price, double prepTime, String toppingName) {
		super(price, prepTime, toppingName);
	}
	
	public Patty() {
		super(1.00, 8000, "Patty");
		updateState();
	}
	
	public void updateState() {
		if(getCookingTime()<=6) {
			setState(CookingState.UNDERCOOKED);
			stateImage="uncookedPatty.png";
		}
		else if(getCookingTime()>6&&getCookingTime()<=11) {
			setState(CookingState.COOKED);
			stateImage="Patty.png";
		}
		else {
			setState(CookingState.OVERCOOKED);
			stateImage="overcookedPatty.png";
		}
	}
	
	public void setState(CookingState state) {
		this.currState=state;
	}
	
	public CookingState getCookingState() {
		updateState();
		return currState;
	}
	
	public String getPattyImage() {
		return stateImage;
	}
}
