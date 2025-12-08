
/**
 * Represents a Patty ingredient that changes its cooking state
 * depending on how long it has been cooked. A Patty may be
 * UNDERCOOKED, COOKED, or OVERCOOKED, and its displayed image
 * updates accordingly.
 */
public class Patty extends CookableToppings {
	public enum CookingState {UNDERCOOKED,COOKED,OVERCOOKED}
	private CookingState currState;
	private String stateImage;
	
	 /**
     * Creates a Patty with custom price, prep time, and name.
     *
     * @param price: The cost of the Patty ingredient
     * @param prepTime: The time needed to prepare it
     * @param toppingName: The name of the topping
     */
	public Patty(double price, double prepTime, String toppingName) {
		super(price, prepTime, toppingName);
	}
	
	/**
     * Default Patty constructor.
     * Creates a Patty costing 1.00 with a prep time of 8 seconds.
     */
	public Patty() {
		super(1.00, 8000, "Patty");
		updateState();
	}
	
	 /**
     * Updates the Patty's cooking state and image depending
     * on how long it has been cooked.
     *
     */
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
	
	  /**
     * Sets the Patty's cooking state.
     *
     * @param state: The new state to assign
     */
	public void setState(CookingState state) {
		this.currState=state;
	}
	
	 /**
     * Returns the current cooking state of the Patty.
     * Automatically updates the state before returning it.
     *
     * @return: The current CookingState
     */
	public CookingState getCookingState() {
		updateState();
		return currState;
	}
	
	/**
     * Returns the file name of the image representing this Patty’s current state.
     *
     * @return: A String file name of the Patty image
     */
	public String getPattyImage() {
		return stateImage;
	}
}
