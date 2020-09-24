/**
This class has one method to implement the interface for the entirety of the program.
Shopping incorporates methods from both the ShoppingBag and GroceryItem classes.
@author Devin Gulati, Emily Tronolone
*/
package project1;

import java.util.Scanner;
import java.text.NumberFormat;

public class Shopping {

	/**
	 * Method used to read user input and utilize the methods from ShoppingBag and
	 * GroceryItem as necessary.
	 */
	public void run() {
		ShoppingBag bag = new ShoppingBag();
		Scanner scanner = new Scanner(System.in); // initializes scanner to read from console
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.println("Let's start shopping!");
		String command = scanner.nextLine();
		while (!command.equals("Q")) { // allow user to enter commands until they quit
			String[] tokens = command.split(" "); // splits each line of input into an array of Strings split by a space character
			switch (tokens[0]) { // accounts for valid commands, except Q
				case "A": {
					try { // handles malformed commands
						bag.add(new GroceryItem(tokens[1], Double.parseDouble(tokens[2]), Boolean.parseBoolean(tokens[3])));
						System.out.println(tokens[1] + " added to the bag.");
						break;
					} catch (Exception e) {
						System.out.println("Invalid command!");
						break;
					}
				}
				case "R": {
					try {
						GroceryItem temp = new GroceryItem(tokens[1], Double.parseDouble(tokens[2]),
								Boolean.parseBoolean(tokens[3]));
						if (bag.remove(temp)) {
							System.out.println(tokens[1] + " " + tokens[2] + " removed.");
						} else {
							System.out.println("Unable to remove, this item is not in the bag.");
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid command!");
						break;
					}
				}
				case "P": {
					if (tokens.length != 1) {
						System.out.println("Invalid command!");
						break;
					}
					bag.print();
					break;
				}
				case "C": {
					if (tokens.length != 1) {
						System.out.println("Invalid command!");
						break;
					}
					if (bag.getSize() == 0) {
						System.out.println("Unable to check out, the bag is empty!");
					} else {
						if (bag.getSize() == 1) {
							System.out.println("**Checking out " + bag.getSize() + " item.");
						} else {
							System.out.println("**Checking out " + bag.getSize() + " items.");
						}
						for (int i = 0; i < bag.getSize(); i++) {
							System.out.println("•" + bag.getGroceryItems()[i].toString());
						}
						double salesPrice = bag.salesPrice();
						double salesTax = bag.salesTax();
						System.out.println("*Sales total: " + formatter.format(salesPrice));
						System.out.println("*Sales tax: " + formatter.format(salesTax));
						System.out.println("*Total amount paid: " + formatter.format(salesPrice + salesTax));
						bag = new ShoppingBag();
					}
					break;
				}
				default: {
					System.out.println("Invalid command!");
					break;
				}
			}
			command = scanner.nextLine(); // iterates through the console input
		}
		if (bag.getSize() != 0) { // checks if bag must be checked out before quitting
			System.out.println("**Checking out " + bag.getSize() + " items.");
			for (int i = 0; i < bag.getSize(); i++) {
				System.out.println("•" + bag.getGroceryItems()[i].toString());
			}
			double salesPrice = bag.salesPrice();
			double salesTax = bag.salesTax();
			System.out.println("*Sales total: " + formatter.format(salesPrice));
			System.out.println("*Sales tax: " + formatter.format(salesTax));
			System.out.println("*Total amount paid: " + formatter.format(salesPrice + salesTax));
			bag = new ShoppingBag();
		}
		System.out.println("Thanks for shopping with us!");
		scanner.close();
		System.exit(0);
	}
}