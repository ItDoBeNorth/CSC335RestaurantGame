package Unit_Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import RestaurantGame.Basket;
import RestaurantGame.Cheese;
import RestaurantGame.EventDetail;
import RestaurantGame.Toppings;

public class EventDetailTest {

	@Test
	void test1() {
		Basket<Toppings> bk = new Basket<Toppings>(1);
		EventDetail event = new EventDetail("eventTest", bk);
		assertEquals(event.getEventInfo(), "eventTest");
		assertEquals((Basket<Toppings>)event.getEventChange(), bk);
	}

}
