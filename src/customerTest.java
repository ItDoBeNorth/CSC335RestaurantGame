import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class customerTest {
	@Test
	void genericCostumerTest() {
		GenericCustomer newCustomer=new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 3);
		
		assertNotNull(newCustomer.getName());
		assertNotNull(newCustomer.getShape());
		assertNotNull(newCustomer.getColor());
		assertEquals(order.size(),3);
	}
	@Test  
	void JohnTest() {
		John John=new John();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
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
	@Test
	void SarahTest() {
		Sarah sarah=new Sarah();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Tomato());
		
		ArrayList<Toppings> newOrder=sarah.getOrder(ingredientsList, 3);
	
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getShape(),"square");
		assertEquals(sarah.getColor(),"blue");
		assertEquals(newOrder.size(),3);
		for (var i=0;i<sarah.getFavoriteOrder().size();i++) {
			assertEquals(sarah.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(sarah.getPersonality(), KnownCustomer.Personality.ACCURATE);
	}
	@Test
	void DavidTest() {
		David david=new David();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Onion());
		
		ArrayList<Toppings> newOrder=david.getOrder(ingredientsList, 3);
	
		assertEquals(david.getName(),"David");
		assertEquals(david.getShape(),"triangle");
		assertEquals(david.getColor(),"orange");
		assertEquals(newOrder.size(),3);
		for (var i=0;i<david.getFavoriteOrder().size();i++) {
			assertEquals(david.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(david.getPersonality(), KnownCustomer.Personality.URGENT);
	}
	@Test
	void MaraihTest() {
		Mariah mariah=new Mariah();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Pickle());
		
		ArrayList<Toppings> newOrder=mariah.getOrder(ingredientsList, 3);
	
		assertEquals(mariah.getName(),"Mariah");
		assertEquals(mariah.getShape(),"triangle");
		assertEquals(mariah.getColor(),"green");
		assertEquals(newOrder.size(),3);
		for (var i=0;i<mariah.getFavoriteOrder().size();i++) {
			assertEquals(mariah.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(mariah.getPersonality(), KnownCustomer.Personality.GENEROUS);
	}
	@Test
	void PeterTest() {
		Peter peter=new Peter();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		
		favOrder.add(new Lettuce());
		
		ArrayList<Toppings> newOrder=peter.getOrder(ingredientsList, 3);
	
		assertEquals(peter.getName(),"Peter");
		assertEquals(peter.getShape(),"circle");
		assertEquals(peter.getColor(),"yellow");
		assertEquals(newOrder.size(),3);
		for (var i=0;i<peter.getFavoriteOrder().size();i++) {
			assertEquals(peter.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(peter.getPersonality(),KnownCustomer.Personality.URGENT);
	}

}
