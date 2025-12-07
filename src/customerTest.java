import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

class customerTest {
	@Test
	void genericCostumerTest() {
		GenericCustomer newCustomer=new GenericCustomer();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> order=newCustomer.getOrder(ingredientsList, 3);
		
		assertNotNull(newCustomer.getName());
		assertNotNull(newCustomer.getShape());
		assertNotNull(newCustomer.getColor());
		assertEquals(order.size(),4);
		newCustomer.startTimer(2);
		assertEquals(newCustomer.timeleft(), 2);
		newCustomer.stopTimer();
		assertFalse(newCustomer.CDisRunning());
	}
	@Test  
	void JohnTest() throws InterruptedException {
		John John=new John();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Cheese());
		
		ArrayList<Toppings> newOrder=John.getOrder(ingredientsList, 3);
	
		assertEquals(John.getName(),"John");
		assertEquals(John.getShape(),"triangle");
		assertEquals(John.getColor(),Color.ORANGE);
		assertEquals(newOrder.size(),3);
		assertEquals(John.getFavoriteOrder().get(1).getClass(),favOrder.get(0).getClass());
		assertEquals(John.patienceLevel(), 3);
		John.startTimer(2);
		assertEquals(John.timeleft(), 2);
		John.stopTimer();
		assertFalse(John.CDisRunning());
		
		
	}
	@Test
	void SarahTest() {
		Sarah sarah=new Sarah();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Patty());
		favOrder.add(new Tomato());
		ArrayList<Toppings> newOrder=sarah.getOrder(ingredientsList, 3);
	
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getShape(),"triangle");
		assertEquals(sarah.getColor(),Color.RED);
		assertEquals(newOrder.size(),3);
		for (var i=0;i<sarah.getFavoriteOrder().size();i++) {
			assertEquals(sarah.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(sarah.getPersonality(), KnownCustomer.Personality.PATIENT);
	}
	@Test
	void DavidTest() {
		David david=new David();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Patty());
		favOrder.add(new Onion());
		
		ArrayList<Toppings> newOrder=david.getOrder(ingredientsList, 3);
	
		assertEquals(david.getName(),"David");
		assertEquals(david.getShape(),"square");
		assertEquals(david.getColor(),Color.DARKGREEN);
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
		favOrder.add(new Patty());
		favOrder.add(new Pickle());
		
		ArrayList<Toppings> newOrder=mariah.getOrder(ingredientsList, 3);
	
		assertEquals(mariah.getName(),"Mariah");
		assertEquals(mariah.getShape(),"circle");
		assertEquals(mariah.getColor(),Color.LIGHTCYAN);
		assertEquals(newOrder.size(),3);
		for (var i=0;i<mariah.getFavoriteOrder().size();i++) {
			assertEquals(mariah.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(mariah.getPersonality(), KnownCustomer.Personality.ACCURATE);
	}
	@Test
	void PeterTest() {
		Peter peter=new Peter();
		ArrayList<Toppings> ingredientsList=new ArrayList<>(List.of(IngredientsList.TOPPINGLIST));
		ArrayList<Toppings> favOrder=new ArrayList<>();
		favOrder.add(new Patty());
		favOrder.add(new Lettuce());
		
		ArrayList<Toppings> newOrder=peter.getOrder(ingredientsList, 3);
	
		assertEquals(peter.getName(),"Peter");
		assertEquals(peter.getShape(),"square");
		assertEquals(peter.getColor(),Color.GREENYELLOW);
		assertEquals(newOrder.size(),3);
		for (var i=0;i<peter.getFavoriteOrder().size();i++) {
			assertEquals(peter.getFavoriteOrder().get(i).getClass(),favOrder.get(i).getClass());
		}
		assertEquals(peter.getPersonality(),KnownCustomer.Personality.URGENT);
	}

}
