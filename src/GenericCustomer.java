import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class GenericCustomer implements Customer {
	private static Random r = new Random();
	private String[] shapes = { "circle" };
	private Color[] colors = { Color.PINK, Color.PURPLE, Color.BLUEVIOLET, Color.DARKBLUE };
	private String[] names = { "Madison", "Henry", "Dany", "Al" }; // will add more
	private String shape;
	private Color color;
	private String name;
	// mult by day to get time patience + 10 sec
	private int patienceLevel;
	private Countdown countdown;

	public GenericCustomer() {
		// customer shape and color (for GUI Implementation)
		Random r = new Random();
		shape = shapes[r.nextInt(shapes.length)];
		color = colors[r.nextInt(colors.length)];
		name = names[r.nextInt(names.length)];
		patienceLevel = r.nextInt(4, 7);
		this.countdown = new Countdown();

	}

	@Override
	public ArrayList<Toppings> getOrder(ArrayList<Toppings> ingredientsList, int maxPick) {
		ArrayList<Toppings> order = new ArrayList<>();
		Patty newPatty = new Patty();
		order.add(newPatty);
		for (int i = 0; i < maxPick; i++) {
			Toppings currTopping = ingredientsList.get(r.nextInt(ingredientsList.size()));
			order.add(currTopping);
		}
		return order;
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

	@Override
	public int patienceLevel() {
		return patienceLevel;
	}

	@Override
	public double timeleft() {
		// TODO Auto-generated method stub
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