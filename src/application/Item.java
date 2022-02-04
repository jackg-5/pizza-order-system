package application;

public abstract class Item {
	//public float price;
	public int amount = 0;

	public Item(float price){
		setPrice(price);
		amount++;

	}
	public int getNumInstances(){
		return amount;
	}


	public abstract void setPrice(float price);

}