package RestaurantGame;
/**
 * CustomerList provides a static list of customer types
 * that can appear in the game. This includes both generic customers
 * and known customers with fixed personalities, shapes, and favorite orders.
 *
 */
public class CustomerList {
	public static Customer[] CUSTOMERS = { new GenericCustomer(), new John(), new GenericCustomer(), new Peter(),
			new Mariah(), new David(), new Sarah(), new GenericCustomer(), new GenericCustomer() };
}
