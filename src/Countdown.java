import java.util.*;

public class Countdown{
	private Timer timer;
	
	public boolean startCountdown(double seconds) {
		if (seconds < 0) {
			return false;
		}
		timer = new Timer();
		TimerTask task = new TimerTask() {
			double timeLeft = seconds;
			@Override
			public void run() {
				System.out.println("Time left: " + timeLeft);
				timeLeft -= 1;
				if (timeLeft == 5.0) {
					System.out.println("5 seconds left");
				}
				if (timeLeft <= 0) {
					System.out.println("Done");
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
		TimerTask task = new TimerTask() {
			int elapsed = 0;
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
	            timer.cancel();
	        }
	}
}
