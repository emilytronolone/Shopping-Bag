/**
First, a single, very descriptive sentence describing the class.
Then, a couple more sentences of description to elaborate.
@author Devin Gulati, Emily Tronolone
*/

package project1;

import java.lang.Math;

public class ShoppingBag {
	private GroceryItem[] bag; // array-based implementation of the bag
	private int size; // number of items currently in the bag
	
	public ShoppingBag() {
		this.bag = new GroceryItem[5];
		this.size = 0;
	}
	
	private int find(GroceryItem item) {
		for(int i = 0; i < this.size; i++) {
			if(item.equals(this.bag[i])) {
				return i;
			}
		}
		return -1;
	} // helper method to find an item
	
	private void grow() {
		GroceryItem[] newBag = new GroceryItem[this.bag.length+5];
		int i = 0;
		for(GroceryItem item : this.bag) {
			newBag[i] = item;
			i++;
		}
		this.bag = newBag;
	} // helper method to grow the capacity
	
	public void add(GroceryItem item) {
		if(this.size >= this.bag.length) {
			grow();
		}
		this.bag[this.size] = item;
		this.size++;
	}
	
	public boolean remove(GroceryItem item) {
		int i = find(item);
		if(i > -1) {
			for(int j = i; j < this.bag.length-1; j++) {
				this.bag[j] = this.bag[i];
			}
			this.bag[bag.length-1] = new GroceryItem();
			this.size--;
			return true;
		}
		return false;
	}
	
	public double salesPrice() {
		double sumOfItems = 0;
		for(int i = 0; i < this.size; i++) {
			double currPrice = this.bag[i].getPrice();
			sumOfItems += currPrice;
		}
		return sumOfItems;
	} // sales total; the sum of the prices in the bag
	
	public double salesTax() {
		double totalTax = 0;
		for(int i = 0; i < this.size; i++) {
			if(this.bag[i].isTaxable()) {
				totalTax += this.bag[i].getPrice()*0.06625;
			}
		}
		totalTax = (double) Math.round(totalTax * 100) / 100;
		return totalTax;
	} // sales tax total of the taxable items in the bag
	
	public void print() {
		if(this.size == 0) {
			System.out.println("The bag is empty!");
		}else{
			System.out.println("**You have " + this.size + " items in the bag.");
			for(int i = 0; i < this.size; i++) {
				System.out.println("•"+ this.bag[i].toString());
			}
			System.out.println("**End of list");
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public GroceryItem[] getGroceryItems() {
		return this.bag;
	}
	
	public static void main(String[] args) {
		//test add, remove, grow, salestax
		ShoppingBag bag1 = new ShoppingBag(); // test cases for ShoppingBag constructor
		ShoppingBag bag2 = new ShoppingBag();
		
		GroceryItem toast = new GroceryItem("toast", 4.99, true); // test cases for parameterized GroceryItem constructor
		GroceryItem jam = new GroceryItem("jam", 1.89, false);
		GroceryItem eggs = new GroceryItem("eggs", 0.99, true);
		GroceryItem butter = new GroceryItem("butter", 0.50, true);
		GroceryItem cheese = new GroceryItem("cheese", 3.49, true);
		GroceryItem turkey = new GroceryItem("turkey", 5.89, false);
		
		bag1.add(toast); // test cases for the parameterized add(GroceryItem item) method and grow() method
		bag1.add(jam);
		bag1.add(eggs);
		bag1.add(butter);
		bag1.add(cheese);
		bag1.add(turkey);
		bag1.add(turkey);
		
		bag2.add(toast);
		bag2.add(jam);
		bag2.add(eggs);
		bag2.add(butter);
		bag2.add(cheese);
		bag2.add(turkey);
		bag2.add(turkey);
		
		bag1.remove(toast); // test cases for the parameterized remove(GroceryItem item) method
		bag2.remove(eggs);
		
		System.out.println("Test Value 1:" + bag1.salesTax()); // test cases for the salesTax() method
		System.out.println("Expected Value 1: ");
		System.out.println("Test Value 2:" + bag2.salesTax());
		System.out.println("Expected Value 2: ");
	}
}