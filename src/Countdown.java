import java.util.*;

public class Countdown{
	private Timer timer;
	public int elapsed = 0;
	public double timeLeft;
	public boolean timeUp;
	public boolean countDownIsRunning;
	
	public boolean startCountdown(double seconds) {
		if (seconds < 0) {
			countDownIsRunning = false;
			return false;}
		stopTimer();
		timeLeft = seconds;
		timeUp = false;
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				countDownIsRunning = true;
				System.out.println("Time left: " + timeLeft);
				timeLeft -= 1;
				if (timeLeft == 5.0) {
					System.out.println("5 seconds left");
				}
				if (timeLeft <= 0) {
					timeUp = true;
					System.out.println("Done");
					countDownIsRunning = false;
					timer.cancel();
				}
				
			}
			
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
		return true;
	}
	
	public void startStopwatch() {
		stopTimer();
		timer = new Timer();
		elapsed = 0;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println(elapsed);
				elapsed++;
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	
	public void stopTimer() {
		 if (timer != null) {
			 	countDownIsRunning = false;
	            timer.cancel();
		 }
	}
	
	public boolean countDownIsRunning() {
		return countDownIsRunning;
	}
	
}
