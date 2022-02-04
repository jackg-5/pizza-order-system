// ControllerClass
// Jack Guebert
// 14jg58
// April 7, 2017

package application;

import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

// ControllerClass is the controller class connected to PizzaGUI2.fxml
public class ControllerClass {
	private static ArrayList<LineItem> orders = new ArrayList<>();

	@FXML
	private ChoiceBox<String> size = new ChoiceBox<>();

	@FXML
	private ChoiceBox<String> cheese = new ChoiceBox<>();

	@FXML
	private ChoiceBox<String> ham = new ChoiceBox<>();

	@FXML
	private ChoiceBox<String> pep = new ChoiceBox<>();

	@FXML
	private Button addToOrder;

	@FXML
	private TextArea orderField;

	@FXML
	private TextField amount;

	@FXML
	private TextField totalCostField;

	@FXML
	private TextField currentPizza;

	private ObservableList<String> sizeList = FXCollections.observableArrayList(
			"Small", "Medium", "Large");

	private ObservableList<String> cheeseList = FXCollections.observableArrayList(
			"Single", "Double", "Triple");

	private ObservableList<String> choiceList = FXCollections.observableArrayList(
			"None", "Single", "Double", "Triple");
	
	// Initializes the controls
		@FXML
		void initialize() {
			size.setItems(sizeList);
			size.valueProperty().addListener((observableValue, oldVal, newVal) ->
			{
				size.setItems(sizeList);
				updateCurrentPizza();
			}); // end lambda function
			cheese.valueProperty().addListener((observableValue, oldVal, newVal) ->
			{
				cheese.setItems(cheeseList);
				updateCurrentPizza();
			}); // end lambda function
			ham.valueProperty().addListener((observableValue, oldVal, newVal) ->
			{
				ham.setItems(choiceList);
				updateCurrentPizza();
			}); // end lambda function
			pep.valueProperty().addListener((observableValue, oldVal, newVal) ->
			{
				pep.setItems(choiceList);
				updateCurrentPizza();
			}); // end lambda function
			size.setValue("Medium");
			cheese.setValue("Single");
			ham.setValue("None");
			pep.setValue("Single");	
			amount.textProperty().addListener((observableValue, oldText, newText) ->
			{
				if (newText != null && !newText.isEmpty()) {
					try {
						int aVal = Integer.parseInt(newText);
						if(aVal < 1 || aVal > 100)
							((StringProperty)observableValue).setValue(oldText);
						updateCurrentPizza();
					} catch (NumberFormatException e) {
						((StringProperty)observableValue).setValue(oldText);
					} // end try-catch
				} // end if
			}); // end lambda function
			totalCostField.textProperty().addListener((observableValue, oldText, newText) ->
			{
				if (newText != null && !newText.isEmpty()) 
					((StringProperty)observableValue).setValue(getTotalCost());
				}); // end lambda function
			currentPizza.setText(makePizza().toString());
			orderField.setText("Order summary:");
		} // end initialize method
		
	// This method writes text to the order summary of the line items
	@FXML
	public void write(){
		String amount = this.amount.getText();
		if(amount == "" || amount == null || amount.isEmpty()){
			currentPizza.setStyle("-fx-text-inner-color: red;");
			currentPizza.setText("Please enter how many pizzas you would like.");
		} // end if
		else{
			int number = Integer.parseInt(amount);
			Pizza pizza = makePizza();
			int search = searchOrders(pizza);
			if (search != -1){
				LineItem item = orders.get(search);
				try {
					item.setNumber(item.getNumber() + number);
				} catch (IllegalPizza e) {
					currentPizza.setStyle("-fx-text-inner-color: red;");
					currentPizza.setText("Too many pizzas! Must be less than 100");
				} // end try/catch
				String s = "Order summary:";
				int line = 1;
				for(LineItem order : orders){
					s += "\n" + line++ + "\t" + order;
				} // end for
				orderField.setText(s);
				updateCost();
			} // end if
			else{
				if(pizza!= null){
					addItem(number, pizza);
					updateCost();
					String s = orderField.getText();
					s+= "\n" + orders.size() + "\t" + orders.get(orders.size()-1);
					orderField.setText(s);
					updateCurrentPizza();
				} // end if
			} // end else
		} // end else
	} // end write method

	// This method updates the text field currentPizza 
	@FXML
	private void updateCurrentPizza(){
		Pizza pizza = makePizza();
		if(pizza!= null)
			currentPizza.setText(pizza.toString());
	} // end update

	// Converts a string (e.g. "Double" to its corresponding integer
	private int toIntMethod(String aString){
		if(aString!= null){
			switch (aString) {
			case "None" :
				return 0;
			case "Single" :
				return 1;
			case "Double" :
				return 2;
			case "Triple" :
				return 3;
			default: return -1;
			} // end switch-case
		} // end if
		else return 0;
	} // end toIntMethod

	// Makes a new pizza based on the choice inputs supplied by the user
	@FXML
	private Pizza makePizza(){
		String size = this.size.valueProperty().get();
		int cheese = toIntMethod(this.cheese.valueProperty().get());
		if(cheese == 0)
			cheese = 1; // 0 is the default return by the toInto method, but 0 is 
		// not a possible value for cheese
		int pep = toIntMethod(this.pep.valueProperty().get());
		int ham = toIntMethod(this.ham.valueProperty().get());
		try {
			currentPizza.setStyle("-fx-text-inner-color: black;");
			Pizza pizza = new Pizza(size, cheese, ham, pep);
			currentPizza.setText(pizza.toString());
			return pizza;
		} catch (IllegalPizza e) {
			currentPizza.setStyle("-fx-text-inner-color: red;");
			currentPizza.setText("Too much meat!");
		} // end try-catch
		return null;
	} // end makePizza method

	// Updates the total cost of the pizza order
	private void updateCost(){
		totalCostField.setText(getTotalCost());
	} // end updateCost

	// Determines the total cost of all the line items
	private String getTotalCost(){
		float totalCost = 0;
		for (LineItem order : orders) {
			totalCost += order.getCost();
		} // end for
		return String.format("Total cost: $%.2f", totalCost);
	} // end getTotalCost method

	// Searches pizza orders to determine if this same pizza has already been ordered
	private static int searchOrders(Pizza pizza) {
		for (int line = 0; line < orders.size(); line++)
			if (orders.get(line).getPizza().equals(pizza))
				return line;
		return -1;
	} // end searchOrders method

	// Adds a new line item, if it is not an Illegal Pizza or illegal amount of pizzas
	private static void addItem(int number, Pizza pizza) {
		try {
			orders.add(new LineItem(number, pizza));
		} catch (IllegalPizza ip) {
			System.out.println(ip.getMessage());
		} // end try-catch
	} // end addItem method
} // end ControllerClass class