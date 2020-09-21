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
}