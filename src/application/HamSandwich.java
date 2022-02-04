package application;

public class HamSandwich extends Item{
	public float price;

	public HamSandwich(float price){
		super(price);
		setPrice(price);	
	}

	public void setPrice(float price){
		this.price = 13;
	}
}