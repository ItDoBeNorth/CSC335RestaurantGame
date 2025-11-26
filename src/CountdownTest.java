import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class CountdownTest {
	
	@Test
	void test1() throws InterruptedException {
		Countdown countdown = new Countdown();
		assertTrue(countdown.startCountdown(10.0));
		Thread.sleep(15000);
		countdown.stopTimer();
		
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
		Thread.sleep(10000);
		countdown.stopTimer();
		Thread.sleep(15000);
		
		
	}
}
