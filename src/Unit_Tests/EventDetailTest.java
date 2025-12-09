package Unit_Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import RestaurantGame.Cheese;
import RestaurantGame.EventDetail;

public class EventDetailTest {

	@Test
	void test1() {
		Cheese cheese = new Cheese();
		EventDetail event = new EventDetail("eventTest", cheese);
		assertEquals(event.getEventInfo(), "eventTest");
		assertEquals(event.getEventChange().toString(), "Cheese@2c78d320");
	}

}
