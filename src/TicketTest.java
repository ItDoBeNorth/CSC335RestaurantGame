
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
	void ticketSizeTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		assertEquals(newTicket.getOrderSize(),3);
	}
	@Test
	void ticketTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		
		assertEquals(newTicket.getCustomer(),newCustomer);
		assertEquals(newTicket.getToppingsList(),order);
	}
	@Test
	void ticketAddToppingTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		newTicket.addTopping(new Cheese());
		
		assertEquals(newTicket.getOrderSize(),4);
		assertEquals(newTicket.getToppingsList().get(3).getClass(), new Cheese().getClass());
	}
	@Test
	void ticketTimerTest() throws InterruptedException {
		GenericCustomer newCustomer= new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		int stopTime = newTicket.stopCountDown();
		assertEquals(stopTime, 0);
		
	}

}
