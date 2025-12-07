import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BurgerBucketTest {
	
	@Test
	void test1() {
		Burger a = new Burger();
		a.addTopping(new Cheese(1.00, "cheese"));
		assertEquals(a.getToppings().get(0).getToppingName(), "cheese");
		a.reset();
		assertTrue(a.getToppings().isEmpty());
		assertEquals(a.RemoveLastTopping(), null);
		a.addTopping(new Cheese(1.00, "cheese"));
		Toppings cheese = a.RemoveLastTopping();
		assertEquals(cheese.getToppingName(), "cheese");
		a.addTopping(new Cheese(1.00, "cheese"));
		Burger burger = new Burger(a);
		assertEquals(burger.getToppings().get(0).getToppingName(), "cheese");
		
	
	}
	
	@Test
	void test2() {
		Cheese c = new Cheese(1.00, "cheese");
		Basket<Toppings> b = new Basket<Toppings>(10);
		b.addIngredient(c);
		assertEquals(b.getList().get(0).getToppingName(), "cheese");
		assertEquals(b.getSize(), 1);
		b.remove(c);
		assertEquals(b.getSize(), 0);
		b.addIngredient(c);
		assertEquals(b.getSize(), 1);
		b.clearBasket();
		assertEquals(b.getSize(), 0);
		b.printList();
		Basket<Toppings> b1 = new Basket<Toppings>(1);
		b1.addIngredient(new Cheese());
		
		assertFalse(b1.addIngredient(new Cheese()));
	}
	
	@Test
	void test3() {
		Burger burger = new Burger();
		Cheese cheese = new Cheese(1.00, "cheese");
		Pickle pickle = new Pickle(1.00, "pickle");
		
		burger.addTopping(cheese);
		burger.addTopping(pickle);
		assertEquals(burger.getToppings().get(0).getToppingName(), "cheese");
		assertEquals(burger.getToppings().get(1).getToppingName(), "pickle");
		
	}
}
