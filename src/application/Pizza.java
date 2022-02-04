package application;
import java.io.Serializable;

/**
 * A class to store Pizza information. 
 * <p>
 * @author Jack Guebert
 * @version 1.0
 */
public class Pizza implements Serializable {

	private static final long serialVersionUID = -728124717043383006L;
    private String size;
    private int cheese;
    private int ham;
    private int pepperoni;
   
    /**
     * Full parameter constructor.
     * @param size The size of the pizza, "small", "medium", or "large".
     * @param cheese The amount of cheese on the pizza, 1, 2, or 3.
     * @param ham The amount of ham on the pizza, 0, 1, 2, or 3.
     * @param pepperoni The amount of pepperoni on the pizza, 0, 1, 2, or 3.
     * @throws IllegalPizza If arguments are not legal.
     */
    public Pizza(String size, int cheese, int ham, int pepperoni) throws IllegalPizza {
    	setSize(size);
        setCheese(cheese);
        setHam(ham);
        setPepperoni(pepperoni,ham);
    } // end Pizza constructor
    
    /**
     * One parameter constructor, when only size is specified. Defaults to a pizza with
     * regular cheese and pepperoni
     * @param size The size of the pizza, "small", "medium", or "large".
     * @throws IllegalPizza If arguments are not legal.
     */ 
    public Pizza(String size) throws IllegalPizza {
    	this(size, 1, 0, 1);
    } // end Pizza constructor
    
    private void setSize(String size) throws IllegalPizza{
    	if(size == null)
    		throw new IllegalPizza("Illegal pizza size (null)");
    	size = size.toLowerCase();
    	if(!size.equals("small") && !size.equals("medium") && !size.equals("large"))
    		throw new IllegalPizza("Illegal pizza size: " + size);
    	this.size = size;
    } // end setSize
    
    private void setCheese(int cheese) throws IllegalPizza{
    	if(cheese < 1 || cheese > 3)
    		throw new IllegalPizza("Illegal amount of cheese: " + cheese);
    	this.cheese = cheese;
    } // end setCheese
    
    private void setHam(int ham) throws IllegalPizza{
    	if(ham < 0 || ham > 3)
    		throw new IllegalPizza("Illegal amount of ham: " + ham);
    	this.ham = ham;
    } // end setHam
    
    private void setPepperoni(int pepperoni, int ham) throws IllegalPizza{
    	if((pepperoni + ham) > 3)
    		throw new IllegalPizza("Too many toppings:" + (pepperoni + ham));
    	if(pepperoni < 0 || pepperoni > 3)
    		throw new IllegalPizza("Illegal amount of pepperoni: " + pepperoni);
    	this.pepperoni = pepperoni;
    } // end setPepperoni
    
    /**
     * Tests two Pizza objects for equality.
     * @return <code>true</code> if all attributes of both objects are equal, <code>false</code>
     * otherwise.
     * @param otherObject The other Pizza object.
     */
    // Overrides the equals method of the Object class.
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Pizza) {
            Pizza otherP = (Pizza)otherObject;
            return size.equals(otherP.size) && cheese == otherP.cheese && ham == 
            		otherP.ham && pepperoni == otherP.pepperoni;
        } // end if
        return false;
    } // end equals
    
    /**
     * Returns the cost of the pizza.
     * @return The cost of the pizza.
     */
    public float getCost(){
    	float cost;
    	switch(size){
    	case "small":
    		cost = 7;
    		break;
    	case "medium":
    		cost = 9;
    		break;
    	case "large":
    		cost = 11;
    		break;
    	default:
    		cost = 0;
    		break;
    	} // end switch-case
    	cost += (cheese + ham + pepperoni-1)*1.50;
    	return cost;
    }
    
    /**
     * A method that converts a number to "double" or "triple".
     * @return "" if the number is 1, "double" if the number is 2, "triple" if the number
     * is 3.
     * @param num An integer, either 1, 2, or 3.
     */
    public String numToPrefix(int num){
    	if(num == 1)
    		return "";
    	if(num == 2)
    		return "double ";
    	return "triple ";
    } // end numToPrefix
    
    /**
     * A String representation of the current object.
     * @return A String representation of the contents of the object containing the values of
     * all the attributes.
     */
    // Overrides the toString method of the Object class.
    @Override
    public String toString(){
    	String s = "";
    	s+= size + " pizza, " + numToPrefix(cheese) + "cheese";
    	if(ham == 0 && pepperoni == 0)
    		s+= " only.";
    	else
    		s+= ", ";
    	if(ham!=0)
    		s+= numToPrefix(ham) + "ham";
    	if(pepperoni!=0 && ham!= 0)
    		s+= ", " + numToPrefix(pepperoni) + "pepperoni.";
    	if(pepperoni != 0 && ham == 0)
    		s+= numToPrefix(pepperoni) + "pepperoni.";
    	if(pepperoni == 0 && ham != 0)
    		s+=".";
    	s+= " Cost: $" + String.format("%.02f", getCost()) + " each.";
    	return s;
    } // end toString

    /**
     * Returns a copy of the of the current Pizza object.
     * @return A copy of the current object.
     */
    // Overrides the clone method in the Object class.
    @Override
    public Pizza clone() {
        try {
            return new Pizza(size, cheese, ham, pepperoni);
        } catch (IllegalPizza e) {
            System.out.println(e.getMessage());
        } //  end try/catch
        return null;
    } // end clone
} // end Pizza class
