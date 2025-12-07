import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class CountdownTest {
	
	@Test
	void test1() throws InterruptedException {
		Countdown countdown = new Countdown();
		assertTrue(countdown.startCountdown(10.0));
		Thread.sleep(1000);
		assertTrue(countdown.countDownIsRunning());
		Thread.sleep(1000);
		countdown.stopTimer();
		Thread.sleep(1000);
		assertFalse(countdown.countDownIsRunning());
		Countdown countdown1 = new Countdown();
		countdown1.startCountdown(6);
		Thread.sleep(7000);
		assertFalse(countdown.countDownIsRunning());
		
	}
	
	@Test
	void test2() throws InterruptedException {
		Countdown countdown = new Countdown();
		assertFalse(countdown.startCountdown(-10.0));
		
	}
	
	@Test
	void test3() throws InterruptedException {
		Countdown countdown = new Countdown();
		countdown.startStopwatch();
		assertEquals(countdown.elapsed, 0);
		
		
	}
}
