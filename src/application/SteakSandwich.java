package application;

public class SteakSandwich extends Item{
	public float price;

	public SteakSandwich(float price){
		super(price);
		setPrice(price);	
	}

	public void setPrice(float price){
		this.price = 12;
	}
}