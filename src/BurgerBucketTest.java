import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BurgerBucketTest {
	
	@Test
	void test1() {
		Burger a = new Burger();
		a.addTopping(new Cheese(1.00, 0.30, "cheese"));
		a.printStack();
		a.reset();
		a.printStack();
		a.addTopping(new Cheese(1.00, 0.30, "cheese"));
		a.printStack();
		a.RemoveLastTopping();
		a.printStack();
	}
	
	@Test
	void test2() {
		Burger a = new Burger();
		Cheese c = new Cheese(1.00, 0.30, "cheese");
		Basket<Toppings> b = new Basket<Toppings>(10);
		b.addIngredient(c);
		b.printList();
	}
	
	@Test
	void test3() {
		Burger burger = new Burger();
		Cheese cheese = new Cheese(1.00, 0.30, "cheese");
		Pickle pickle = new Pickle(1.00, 0.30, "pickle");
		
		burger.addTopping(cheese);
		burger.addTopping(pickle);
		assertEquals(burger.getToppings().get(0).getToppingName(), "cheese");
		assertEquals(burger.getToppings().get(1).getToppingName(), "pickle");
		//System.out.println(burger.getToppings().get(0).getToppingName());
	}
}
