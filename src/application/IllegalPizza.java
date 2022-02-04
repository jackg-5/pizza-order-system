package application;
/**
 * An Exception thrown by the Pizza Object if parameters are not legal.
 * <ul>
 * <li>The size of the pizza can only be "small", "medium", or "large".</li>
 * <li>The amount of cheese can only be 1, 2, or 3.</li>
 * <li>The amount of ham can be 0, 1, 2, or 3.</li>
 * <li>The amount of pepperoni can be 0, 1, 2, or 3.</li>
 * <li>The amount of ham plus pepperoni can not be greater than 3.</li>
 * </ul>
 * @author Jack Guebert
 * @version 1.0
 */

public class IllegalPizza extends Exception {
	private static final long serialVersionUID = -5935590159508055457L;
	/**
	 * Passes the message supplied to the exception.
	 * @param message A more specific message.
	 */
	public IllegalPizza(String message) {
		super(message);
	} // end IllegalPizza

} // end IllegalHalloweenException