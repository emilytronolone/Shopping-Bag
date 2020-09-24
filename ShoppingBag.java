/**
This class allows for the creation of a new shopping bag which can be added and removed from.
The class contains the essential methods for ShoppingBag to operate.
In addition to adding and removing, this class also contains methods for printing and calculating sales tax and sales price.
Two getter methods are included so other classes can access this class' data members.
There is a testbed main method to test .add(), .remove(), .grow(), and .salesTax().
@author Devin Gulati, Emily Tronolone
*/

package project1;

import java.lang.Math;

public class ShoppingBag {
	private GroceryItem[] bag; // array-based implementation of the bag
	private int size; // number of items currently in the bag

	/**
	 * Constructor to create a new shopping bag. Consists of an array to represent
	 * the GroceryItems in the ShoppingBag. Includes size which is the number of
	 * GroceryItems in the ShoppingBag.
	 */
	public ShoppingBag() {
		this.bag = new GroceryItem[5];
		this.size = 0;
	}

	/**
	 * helper method to find GroceryItem in the bag
	 * 
	 * @param item GroceryItem to be found
	 * @return index of GroceryItem of found, -1 if not found
	 */
	private int find(GroceryItem item) {
		for (int i = 0; i < this.size; i++) {
			if (item.equals(this.bag[i])) {
				return i;
			}
		}
		return -1;
	} // helper method to find an item

	/**
	 * Helper method to increase capacity of bag by 5.
	 */
	private void grow() {
		GroceryItem[] newBag = new GroceryItem[this.bag.length + 5];
		int i = 0;
		for (GroceryItem item : this.bag) {
			newBag[i] = item;
			i++;
		}
		this.bag = newBag;
	} // helper method to grow the capacity

	/**
	 * Adds GroceryItem to the bag.
	 * 
	 * @param item GroceryItem to be added
	 */
	public void add(GroceryItem item) {
		if (this.size >= this.bag.length) {
			grow();
		}
		this.bag[this.size] = item;
		this.size++;
	}

	/**
	 * Removes GroceryItem from the bag.
	 * 
	 * @param item GroceryItem to be removed
	 * @return true on success, false if item is not in bag
	 */
	public boolean remove(GroceryItem item) {
		int i = find(item);
		if (i > -1) {
			/*for (int j = i; j < this.size; j++) {
				this.bag[j] = this.bag[j + 1]; // update the remainder of the array
			}*/
			this.bag[i] = this.bag[this.size-1];
			this.bag[bag.length - 1] = new GroceryItem();
			this.size--;
			return true;
		}
		return false;
	}

	/**
	 * Calculates total price of items in bag before tax is applied.
	 * 
	 * @return total price of items in bag before tax is applied
	 */
	public double salesPrice() {
		double sumOfItems = 0;
		for (int i = 0; i < this.size; i++) {
			double currPrice = this.bag[i].getPrice();
			sumOfItems += currPrice;
		}
		return sumOfItems;
	} // sales total; the sum of the prices in the bag

	/**
	 * Calculates sales tax total of the taxable items in the bag.
	 * 
	 * @return sales tax total of the taxable items in the bag
	 */
	public double salesTax() {
		double totalTax = 0;
		for (int i = 0; i < this.size; i++) {
			if (this.bag[i].isTaxable()) {
				totalTax += this.bag[i].getPrice() * 0.06625;
			}
		}
		totalTax = (double) Math.round(totalTax * 100) / 100; // ensures a maximum of 2 decimal places of precision
		return totalTax;
	} // sales tax total of the taxable items in the bag

	/**
	 * Prints contents of the bag to the console.
	 */
	public void print() {
		if (this.size == 0) {
			System.out.println("The bag is empty!");
		} else {
			System.out.println("**You have " + this.size + " items in the bag.");
			for (int i = 0; i < this.size; i++) {
				System.out.println("•" + this.bag[i].toString());
			}
			System.out.println("**End of list");
		}
	}

	/**
	 * Getter method to access the size of the bag.
	 * 
	 * @return the number of items in the bag
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Getter method to access the array of items in the bag.
	 * 
	 * @return array of all GroceryItems in the bag
	 */
	public GroceryItem[] getGroceryItems() {
		return this.bag;
	}

	/**
	 * Testbed main method to test .add(), .remove(), .grow(), and .salesTax().
	 * 
	 * @param args from the command line
	 */
	public static void main(String[] args) {
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

		bag1.print();
		bag2.print();

		bag1.remove(toast); // test cases for the parameterized remove(GroceryItem item) method
		bag2.remove(eggs);

		bag1.print();
		bag2.print();

		System.out.println("Test Value 1: " + bag1.salesTax()); // test cases for the salesTax() method
		System.out.println("Expected Value 1: 0.33");
		System.out.println("Test Value 2: " + bag2.salesTax());
		System.out.println("Expected Value 2: 0.59");
	}
}