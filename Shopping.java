/**
This class has one method to implement the interface for the entirety of the program.
Shopping incorporates methods from both the ShoppingBag and GroceryItem classes.
@author Devin Gulati, Emily Tronolone
*/

package project1;

import java.util.Scanner;

public class Shopping {
	public void run() {
		ShoppingBag bag = new ShoppingBag();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Let's start shopping!");
		String command = scanner.nextLine();
		while(!command.equals("Q")) {
			String[] tokens = command.split(" ");
			switch(tokens[0]) {
				case "A":{
					try {
						bag.add(new GroceryItem(tokens[1], Double.parseDouble(tokens[2]), Boolean.parseBoolean(tokens[3])));
						System.out.println(tokens[1] + " added to the bag.");
						break;
					}catch (Exception e) {
						System.out.println("Invalid command!");
						break;
					}
				}
				case "R":{
					try {
						if(bag.remove(new GroceryItem(tokens[1], Double.parseDouble(tokens[2]), Boolean.parseBoolean(tokens[3])))) {
							System.out.println(tokens[1] + " " + tokens[2] + " removed.");
						}else {
							System.out.println("Unable to remove, this item is not in the bag.");
						}
						break;
					}catch (Exception e) {
						System.out.println("Invalid command!");
						break;
					}
				}
				case "P":{
					if(tokens.length != 1) {
						System.out.println("Invalid command!");
						break;
					}
					bag.print();
					break;
				}
				case "C":{
					if(tokens.length != 1) {
						System.out.println("Inavlid command!");
						break;
					}
					if(bag.getSize()==0) {
						System.out.println("Unable to check out, the bag is empty!");
					}else {
						System.out.println("**Checking out " + bag.getSize() + " items.");
						for(int i = 0; i < bag.getSize(); i++) {
							System.out.println("•"+ bag.getGroceryItems()[i].toString());
						}
						double salesPrice = bag.salesPrice();
						double salesTax = bag.salesTax();
						System.out.println("*Sales total: $" + salesPrice);
						System.out.println("*Sales tax: $" + salesTax);
						System.out.println("*Total amount paid: $" + (salesPrice + salesTax));
						bag = new ShoppingBag();
					}
					break;
				}
				default:{
					System.out.println("Invalid command!");
					break;
				}
			}
			command = scanner.nextLine();
		}
		if(bag.getSize()!=0) {
			System.out.println("**Checking out " + bag.getSize() + " items.");
			for(int i = 0; i < bag.getSize(); i++) {
				System.out.println("•"+ bag.getGroceryItems()[i].toString());
			}
			double salesPrice = bag.salesPrice();
			double salesTax = bag.salesTax();
			System.out.println("*Sales total: $" + salesPrice);
			System.out.println("*Sales tax: $" + salesTax);
			System.out.println("*Total amount paid: $" + (salesPrice + salesTax));
			bag = new ShoppingBag();
		}
		System.out.println("Thanks for shopping with us!");
		scanner.close();
		System.exit(0);
	}
}