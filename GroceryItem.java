package project1;

public class GroceryItem {
	private String name;
	private double price;
	private boolean taxable;

	public GroceryItem() {
		
	}
	
	public GroceryItem(String name, double price, boolean taxable) {
		this.name = name;
		this.price = price;
		this.taxable = taxable;
	}
	
	public boolean equals(Object obj){
		GroceryItem item = (GroceryItem) obj;
		if(item.name == this.name && item.price == this.price && item.taxable == this.taxable) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		if(this.taxable) {
			return this.name + ": $" + this.price + " : is taxable";
		}else {
			return this.name + ": $" + this.price + " : tax free";
		}
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public boolean isTaxable() {
		return this.taxable;
	}
}