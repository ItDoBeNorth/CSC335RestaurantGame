package Unit_Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import RestaurantGame.Patty;

public class CookableToppingsTest {

	@Test
	void PattyTest() throws InterruptedException {
		Patty patty = new Patty();
		assertEquals(patty.getCookingTime(), 0);
		assertEquals(patty.getPrepTime(), 8000);
		assertEquals(patty.getPrice(), 1.00);
		assertEquals(patty.getToppingName(), "Patty");
		assertEquals(patty.getPattyImage(), "uncookedPatty.png");
		patty.getCookingState();

	}

	@Test
	void PattyTest2() throws InterruptedException {
		Patty patty = new Patty(1.00, 2.00, "patty");
		// patty.startCooking();
		// Thread.sleep(16000);
		assertEquals(patty.getPattyImage(), "Patty.png");
	}
}
