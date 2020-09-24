/**
This class represents GroceryItems that can be added to a ShoppingBag.
There are two constructors, one is empty and one is parameterized.
There are also two getter methods so other classes can access its data members.
@author Devin Gulati, Emily Tronolone
*/

package project1;
import java.text.NumberFormat;

public class GroceryItem {
	private String name;
	private double price;
	private boolean taxable;

	/**
     * Empty constructor to create placeholder GroceryItems.
     */
	public GroceryItem() {

	}
	
	/**
     * Constructor for GroceryItem.
     * @param name: name of this GroceryItem
     * @param price: price of this GroceryItem
     * @param taxable: boolean representing if this GroceryItem is taxable or not
     */
	public GroceryItem(String name, double price, boolean taxable) {
		this.name = name;
		this.price = price;
		this.taxable = taxable;
	}

	/**
     * Compares this GroceryItem to another to determine if they are equal.
     * @param obj GroceryItem to be compared to
     * @return true if they are equal, false otherwise
     */
	public boolean equals(Object obj) {
		GroceryItem item = (GroceryItem) obj;
		if (item.name == this.name && item.price == this.price && item.taxable == this.taxable) {
			return true;
		}
		return false;
	}

	/**
     * Concatenates the values of price, name, and taxable into a String.
     * @return the values of price, name, and taxable as a combined String
     */
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		if (this.taxable) {
			return this.name + ": " + formatter.format(this.price) + " : is taxable";
		} else {
			return this.name + ": " + formatter.format(this.price) + " : tax free";
		}
	}
	
	/**
	 * Getter method to access the price data member of a GroceryItem.
	 * @return price of GroceryItem
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Getter method to return the taxable data member of a GroceryItem.
	 * @return true if item if taxable, false otherwise
	 */
	public boolean isTaxable() {
		return this.taxable;
	}
}