import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BurgerBucketTest {
	
	@Test
	void test1() {
		Burger a = new Burger(5.00, 1.00);
		System.out.println(a.getPrice() + ", " + a.getPrepTime());
		a.addTopping(new Cheese(1.00, 0.30));
		a.printStack();
		a.reset();
		a.printStack();
		a.addTopping(new Cheese(1.00, 0.30));
		a.printStack();
		a.RemoveLastTopping();
		a.printStack();
	}
	
	@Test
	void test2() {
		Burger a = new Burger(5.00, 1.00);
		Cheese c = new Cheese(1.00, 0.30);
		Basket b = new Basket();
		b.addIngredient(a);
		b.addIngredient(c);
		b.printList();
	}
}
