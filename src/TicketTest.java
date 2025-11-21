import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TicketTest {

	@Test
	void ticketSizeTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		IngredientsList ingredientsList=new IngredientsList();
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList.TOPPINGLIST, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		
		assertEquals(newTicket.getOrderSize(),2);
	}
	@Test
	void ticketTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		IngredientsList ingredientsList=new IngredientsList();
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList.TOPPINGLIST, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		
		assertEquals(newTicket.getCustomer(),newCustomer);
		assertEquals(newTicket.getToppingsList(),order);
	}
	@Test
	void ticketAddToppingTest() {
		GenericCustomer newCustomer= new GenericCustomer();
		IngredientsList ingredientsList=new IngredientsList();
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList.TOPPINGLIST, 2);
		Ticket newTicket=new Ticket(newCustomer,order);
		newTicket.addTopping(new Cheese());
		
		assertEquals(newTicket.getOrderSize(),3);
		assertEquals(newTicket.getToppingsList().get(2).getClass(),new Cheese().getClass());
	}

}
