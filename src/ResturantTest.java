import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResturantTest {

	@Test
	void newControllerTest() {
		RestaurantController controller = new RestaurantController();
		assertNotNull(controller);
		assertNull(controller.getModel());
		assertNotNull(controller.getPlayerList());

		controller.processPlayerName("newPlayer");
		assertNotNull(controller.getModel());
	}

	@Test
	void toolsTest() {
		RestaurantController controller = new RestaurantController();
		controller.processPlayerName("newPlayer");

		controller.setUpDay();
		assertFalse(controller.isDayOver());
		assertEquals(controller.getCurrDay(), 1);

		// Basket tests
		controller.addToBasket(controller.getDaysToppings().get(0));
		controller.addToBasket(controller.getDaysToppings().get(0));

		assertEquals(controller.getCurrBasket().getList().size(), 2);
		controller.removeFromBasket(controller.getCurrBasket().getList().getFirst());
		assertEquals(controller.getCurrBasket().getList().size(), 1);
		controller.resetBasket();
		assertEquals(controller.getCurrBasket().getList().size(), 0);

		// burger tests
		controller.addToBurger(controller.getDaysToppings().get(0));
		controller.addToBurger(controller.getDaysToppings().get(0));
		controller.addToBurger(controller.getDaysToppings().get(0));

		assertEquals(controller.getBurger().getToppings().size(), 3);
		controller.undoBurger();
		assertEquals(controller.getBurger().getToppings().size(), 2);
		controller.resetBurger();
		assertEquals(controller.getBurger().getToppings().size(), 0);

		// oven tests
		Patty newPatty = new Patty();
		controller.addToOven(newPatty);
		assertEquals(controller.getCurrOven().getList().size(), 1);
		controller.removeFromOven(newPatty);
		assertEquals(controller.getCurrOven().getList().size(), 0);

	}

	@Test
	void newGameTest() {
		RestaurantController controller = new RestaurantController();
		controller.processPlayerName("newPlayer");
		RestaurantModel model = controller.getModel();
		controller.nextDay();
		;

		for (int i = 0; i < model.getCurrCustomers().length; i++) {
			Customer currCustomer = model.getCurrCustomers()[i];
			Ticket currTicket = controller.getCurrentTicket(currCustomer);

			for (Toppings t : currTicket.getToppingsList()) {
				controller.addToBurger(t);
			}

			controller.serveBurger(i, currTicket);

		}

		assertTrue(controller.getDaysAccuracy() > 0);
		assertTrue(controller.getDaysTiming() > 0);
		assertTrue(controller.getDaysIncome() > 0);
		assertTrue(controller.getDaysScore() > 0);
		assertEquals(controller.getDayMilestones(), "");
	}

	@Test
	void lowAccuracyTest() {
		RestaurantController controller = new RestaurantController();
		controller.processPlayerName("newPlayer");
		RestaurantModel model = controller.getModel();
		controller.nextDay();
		;
		for (int i = 0; i < model.getCurrCustomers().length; i++) {
			Customer currCustomer = model.getCurrCustomers()[i];
			controller.updateTaskList(i, currCustomer);
			controller.getCurrentCustomers();

		}
		assertTrue(controller.getDaysAccuracy() < 100);

	}

	@Test
	void tenDaysTest() {
		RestaurantController controller = new RestaurantController();
		controller.processPlayerName("newPlayer");
		RestaurantModel model = controller.getModel();
		controller.nextDay();

		for (int day = 0; day < 9; day++) {
			for (int i = 0; i < model.getCurrCustomers().length; i++) {
				Customer currCustomer = model.getCurrCustomers()[i];
				Ticket currTicket = controller.getCurrentTicket(currCustomer);
				controller.serveBurger(i, currTicket);
			}
			model.nextDay();
		}
		assertEquals(controller.getCurrDay(), 10);

	}

}
