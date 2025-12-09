import java.util.*;
/**
 * Countdown creates two types of timer:
 *
 *  1. A countdown timer (startCountdown) that counts down from a specified number of seconds.
 *
 *  2. A stopwatch timer (startStopwatch) that counts upward from zero.
 *
 * The timers use Java's Timer and TimerTask to update once per second.
 */
public class Countdown{
	private Timer timer;
	public int elapsed = 0;
	public double timeLeft;
	public boolean timeUp;
	public boolean countDownIsRunning;
	
	 /**
      * Starts a countdown from the given number of seconds.
      * Updates once per second until time reaches zero.
      *
      * @param seconds: The number of seconds to count down from
      * @return true if the countdown started successfully, false if input was invalid
      */
	public boolean startCountdown(double seconds) {
		if (seconds < 0) {
			countDownIsRunning = false;
			return false;
		}
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
	
	 /**
      * Starts the stopwatch timer, counting upward from zero.
      * Prints the elapsed time every second.
      */
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
	
	 /**
      * Stops whichever timer (countdown or stopwatch) is currently running.
      */
	public void stopTimer() {
		if (timer != null) {
			countDownIsRunning = false;
			timer.cancel();
		}
	}
	
	/**
     * Returns whether the countdown timer is currently active.
     *
     * @return true if countdown is active, false otherwise
     */
	public boolean countDownIsRunning() {
		return countDownIsRunning;
	}

}
