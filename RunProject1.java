/**
This RunProject1 class is intended to run the entirety of the program.
By having the main method in this class, we are able to also utilize the Shopping, ShoppingBag, and GroceryItem classes.
@author Devin Gulati, Emily Tronolone
*/

package project1;

/**
 * Main method used to run the entire program and utilize the other three
 * classes.
 */
public class RunProject1 {
	public static void main(String[] args) {
		new Shopping().run();
	}
}