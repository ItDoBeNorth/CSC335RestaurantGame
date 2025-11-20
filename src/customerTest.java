import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class customerTest {
	@Test
	void genericCostumerTest() {
		GenericCustomer newCustomer=new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>();
		ingredientsList.add(new Cheese());
		ingredientsList.add(new Tomato());
		ingredientsList.add(new Pickle());
		ingredientsList.add(new Onion());
		ingredientsList.add(new Lettuce());
		
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 3);
		
		assertNotNull(newCustomer.getName());
		assertNotNull(newCustomer.getShape());
		assertNotNull(newCustomer.getColor());
		assertEquals(order.size(),3);
	}
	@Test
	void JohnTest() {
		John John=new John();
		ArrayList<Toppings> ingredientsList=new ArrayList<>();
		ingredientsList.add(new Cheese());
		ingredientsList.add(new Tomato());
		ingredientsList.add(new Pickle());
		ingredientsList.add(new Onion());
		ingredientsList.add(new Lettuce());
		
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Cheese());
		
		ArrayList<Toppings> newOrder=John.getOrder(ingredientsList, 3);
	
		assertEquals(John.getName(),"John");
		assertEquals(John.getShape(),"circle");
		assertEquals(John.getColor(),"red");
		assertEquals(newOrder.size(),3);
		for (var i=0;i<John.getFavoriteOrder().size();i++) {
			assertEquals(John.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
	}

}
