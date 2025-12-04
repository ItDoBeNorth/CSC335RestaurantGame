import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class KnownCustomer implements Customer {
	protected String name;
	protected String shape;
	protected Color color;
	
	protected final ArrayList<Toppings> favoriteOrder;
	public enum Personality {ACCURATE, URGENT, GENEROUS, PATIENT;}
	private Personality personality;
	// mult by day to get time patience + 10 sec
	private int patienceLevel;
	private Countdown countdown;
	
	public KnownCustomer(String name,String shape, Color color, ArrayList<Toppings> favoriteOrder,Personality personality, int patienceLevel) {
		this.name=name;
		this.shape=shape;
		this.color=color;
		this.favoriteOrder=favoriteOrder;
		this.personality=personality;
		this.patienceLevel = patienceLevel;
		this.countdown = new Countdown();
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
	public Color getColor() {
		return color;
	}
	public ArrayList<Toppings> getFavoriteOrder(){
		return favoriteOrder;
	}
	public Personality getPersonality() {
		return personality;
	}
	@Override
	public int patienceLevel() {
		return patienceLevel;
	}
	
	@Override
	public double timeleft() {
		return countdown.timeLeft;
	}
	
	@Override
	public void startTimer(double time) {
		countdown.startCountdown(time);
		
	}
	
	@Override
	public void stopTimer() {
		countdown.stopTimer();
		
	}
	
	@Override
	public boolean CDisRunning() {
		return countdown.countDownIsRunning;
	}
	
}
