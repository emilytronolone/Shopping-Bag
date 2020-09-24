/**
This class has one method to implement the interface for the entirety of the program.
Shopping incorporates methods from both the ShoppingBag and GroceryItem classes.
@author Devin Gulati, Emily Tronolone
*/

package project1;

import java.util.Scanner;

public class Shopping {

	/**
	 * Helper method to display contents of the bag and total price and tax of the
	 * items in the bag.
	 * @param bag the ShoppingBag that is being checked out
	 */
	private void checkOut(ShoppingBag bag) {
		System.out.println("Checking out " + bag.getSize() + " item(s)");

		for (int i = 0; i < bag.getSize(); i++) {
			System.out.println((bag.getGroceryItems()[i]).toString());
		}
		double salesPrice = bag.salesPrice();
		double salesTax = bag.salesTax();
		double total = Math.round((salesPrice + salesTax) * 100) / 100d; // ensures a maximum of 2 decimal places of
																			// precision
		String priceFormatted = "" + salesPrice;
		if (priceFormatted.charAt(0) == '.') { // ensures a 0 in front of prices less than $1
			priceFormatted = "0" + priceFormatted;
		}
		if (priceFormatted.charAt(priceFormatted.length() - 2) == '.') { // ensures 2 decimal places
			priceFormatted += "0";
		}
		String taxFormatted = "" + salesTax;
		if (taxFormatted.charAt(0) == '.') {
			taxFormatted = "0" + taxFormatted;
		}
		if (taxFormatted.charAt(taxFormatted.length() - 2) == '.') {
			taxFormatted += "0";
		}
		String totalFormatted = "" + total;
		if (totalFormatted.charAt(0) == '.') {
			totalFormatted = "0" + total;
		}
		if (totalFormatted.charAt(totalFormatted.length() - 2) == '.') {
			totalFormatted += "0";
		}
		System.out.println("Sales total: $" + priceFormatted);
		System.out.println("Sales tax: $" + taxFormatted);
		System.out.println("Total amount paid: $" + totalFormatted);
	}

	/**
	 * Method used to read user input and utilize the methods from ShoppingBag and
	 * GroceryItem as necessary.
	 */
	public void run() {
		ShoppingBag bag = new ShoppingBag();
		Scanner scanner = new Scanner(System.in); // initializes scanner to read from console
		scanner.useDelimiter("\n");
		String command = scanner.nextLine();
		System.out.println("Let's start shopping!");
		while (!command.equals("Q")) { // reads from the scanner until the Quit command is read
			if (command.equals("Let's start shopping!")) {
				continue;
			}
			String[] tokens = command.split(" "); // splits each line of input into an array of Strings split by " "
			switch (tokens[0]) { // first element of this array should always be the command
				case "A": {
					try { // handles malformed commands
						bag.add(new GroceryItem(tokens[1], Double.parseDouble(tokens[2]), Boolean.parseBoolean(tokens[3])));
						System.out.println(tokens[1] + " added to the bag.");
					} catch (Exception e) {
						System.out.println("Invalid Command!");
						break;
					}
					break;
				}
				case "R": {
					try {
						if (bag.remove(new GroceryItem(tokens[1], Double.parseDouble(tokens[2]),
								Boolean.parseBoolean(tokens[3])))) {
							System.out.println(tokens[1] + " " + tokens[2] + " removed.");
						} else {
							System.out.println("Unable to remove, this item is not in the bag.");
						}
					} catch (Exception e) {
						System.out.println("Invalid Command!");
						break;
					}
					break;
				}
				case "P": {
					if (tokens.length != 1) {
						System.out.println("Invalid Command!");
						break;
					}
					bag.print();
					break;
				}
				case "C": {
					if (tokens.length != 1) {
						System.out.println("Invalid Command!");
						break;
					}
					if (bag.getSize() == 0) {
						System.out.println("Unable to checkout, the bag is empty!");
					} else {
						checkOut(bag);
						bag = new ShoppingBag();
					}
					break;
				}
				default: { // catches all invalid commands
					System.out.println("Invalid Command!");
					break;
				}
			}
			command = scanner.nextLine(); // iterates through the console input
		}
		if (bag.getSize() != 0) { // checks if bag has been checked out before quitting
			checkOut(bag);
		}
		System.out.println("Thanks for shopping with us!");
		scanner.close();
		System.exit(0);
	}
}