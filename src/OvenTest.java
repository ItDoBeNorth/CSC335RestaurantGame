import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OvenTest {

	@Test
	void OvenTest() {
		Oven<Toppings> oven = new Oven<Toppings>(3);
		assertEquals(oven.getSize(), 0);
		oven.addIngredient(new Cheese());
		assertEquals(oven.getSize(), 1);
		oven.addIngredient(new Cheese());
		oven.addIngredient(new Cheese());
		assertFalse(oven.addIngredient(new Cheese()));
		assertEquals(oven.getList().get(2).getToppingName(), "cheese");
		//assertEquals(oven.remove(new Cheese()), "cheese");
		oven.clearOven();
		
		assertEquals(oven.getSize(), 0);
		Toppings pickle = new Pickle();
		oven.addIngredient(pickle);
		oven.printList();
		assertEquals(oven.remove(pickle).getToppingName(), "pickle");
		
	}
	
	
}
