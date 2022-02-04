package application;
import java.io.Serializable;
/**
 * A class to store the pizza, along with the amount of pizzas orderd.
 * <p>

 * @author Jack Guebert
 * @version 1.0
 */
public class LineItem implements Comparable<LineItem>, Serializable {

	private static final long serialVersionUID = -5090767330478541647L;
	private Pizza pizza1;
	private int number;

	/**
	 * Full parameter constructor.
	 * @param num The amount of pizzas ordered.
	 * @param pizza1 The type of pizza being ordered.
	 * @throws IllegalPizza If arguments are not legal.
	 */
	public LineItem(int num, Pizza pizza1) throws IllegalPizza {
		setPizza(pizza1);
		setNumber(num);
	} // end LineItem constructor

	/**
	 * One parameter constructor.
	 * @param pizza1 The type of pizza being ordered.
	 * @throws IllegalPizza If arguments are not legal.
	 */
	public LineItem(Pizza pizza1) throws IllegalPizza{
		this(1, pizza1);
	} // end LineItem constructor

	private void setPizza(Pizza pizza) throws IllegalPizza{
		if(pizza == null)
			throw new IllegalPizza("Illegal pizza");
		pizza1 = pizza;
	} // end setPizza

	/**
	 * Mutator for number of pizzas
	 * @param num The number of pizzas, between 1 and 100.
	 * @throws IllegalPizza if the amount of pizzas is not between 1 and 100, inclusive
	 */
	public void setNumber(int num) throws IllegalPizza{
		if(num < 1 || num > 100)
			throw new IllegalPizza("Illegal amount of pizzas: " + number);
		number = num;
	} // end setNumber

	/**
	 * Compares LineItem objects on the basis of the cost of the line item.
	 * @param otherI The other LineItem object.
	 * @return A negative <code>int</code> if the supplied object had more vistors, zero if they have the same
	 * number and a positive number if the current object has more visitors.
	 */
	public int compareTo(LineItem otherI){
		if(Math.abs(otherI.getCost() - getCost()) < 1)
			return 0;
		if((otherI.getCost() - getCost()) < 0)
			return -1;
		return 1;	
	} // end compareTo

	/**
	 * Returns the cost of the line item.
	 * @return The cost of the line item.
	 */
	public float getCost(){
		return number * pizza1.getCost();
	} // end getCost

	/**
	 * Returns the Pizza object.
	 * @return The Pizza object.
	 */
	public Pizza getPizza(){
		return pizza1;
	} // end getPiza

	/**
	 * Returns the number of pizzas in the line item.
	 * @return The number of of pizzas in the line item.
	 */
	public int getNumber(){
		return number;
	} // end getNumber

	/**
	 * A String representation of the current object.
	 * @return A String representation of the contents of the object containing the values of
	 * all the attributes.
	 */
	// Overrides the toString method of the Object class.
	@Override
	public String toString(){
		return number + " " + pizza1;
	} // end toString
} // end LineItem class

