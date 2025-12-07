import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class ToppingTest {
	
	@Test
	void CheeseTest() {
		Cheese cheese = new Cheese();
		assertEquals(cheese.getPrice(), .50);
		assertEquals(cheese.getToppingName(), "cheese");
		Cheese cheese2 = new Cheese(1, "T2");
		assertEquals(cheese2.getPrice(), 1);
		assertEquals(cheese2.getToppingName(), "T2");
	}
	
	@Test
	void OnionTest() {
		Onion onion = new Onion();
		assertEquals(onion.getPrice(), .80);
		assertEquals(onion.getToppingName(), "onion");
		Onion onion2 = new Onion(1, "T2");
		assertEquals(onion2.getPrice(), 1);
		assertEquals(onion2.getToppingName(), "T2");
	}
	
	@Test
	void TomatoTest() {
		Tomato tomato = new Tomato();
		assertEquals(tomato.getPrice(), .75);
		assertEquals(tomato.getToppingName(), "tomato");
		Tomato tomato1 = new Tomato();
		assertTrue(tomato.isTheSameIngredient(tomato1));
		Tomato tomato2 = new Tomato(1, "T2");
		assertEquals(tomato2.getPrice(), 1);
		assertEquals(tomato2.getToppingName(), "T2");
		assertFalse(tomato.isTheSameIngredient(tomato2));
		
		
	}
	
	@Test
	void LettuceTest() {
		Lettuce lettuce = new Lettuce();
		assertEquals(lettuce.getPrice(), .25);
		assertEquals(lettuce.getToppingName(), "lettuce");
		Lettuce lettuce2 = new Lettuce(1, "T2");
		assertEquals(lettuce2.getPrice(), 1);
		assertEquals(lettuce2.getToppingName(), "T2");
	}
	
	@Test
	void PickleTest() {
		Pickle pickle = new Pickle();
		assertEquals(pickle.getPrice(), 1.00);
		assertEquals(pickle.getToppingName(), "pickle");
		Pickle pickle2 = new Pickle(1, "T2");
		assertEquals(pickle2.getPrice(), 1);
		assertEquals(pickle2.getToppingName(), "T2");
	}
	
	
}
