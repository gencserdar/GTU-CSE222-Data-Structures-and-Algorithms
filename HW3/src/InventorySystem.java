import java.util.Scanner;
import java.util.Date;
import java.lang.System;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The InventorySystem program provides a user interface for managing an electronics inventory.
 *  The system allows users to:
 *  Add a new device to the inventory,
 *  remove a device from the inventory,
 *  update details of a device in the inventory,
 *  list all devices in the inventory,
 *  find the cheapest device in the inventory,
 *  sort all devices by price,
 *  calculate the total value of the inventory,
 *  restock a device in the inventory,
 *  export an inventory report to a file named 'report.txt'.
 */
public class InventorySystem {
	/** Default constructor for the InventorySystem class.*/
	public InventorySystem() {
    }
	/**
     * The main method of the InventorySystem class.
     * It displays a menu to the user and handles user input to perform various operations on the inventory.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        System.out.println("\nWelcome to the Electronics Inventory Management System!");
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.printf("Enter category name: ");
                    String category = scanner.nextLine();
                    System.out.printf("Enter device name: ");
                    String name = scanner.nextLine();
                    System.out.printf("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.printf("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    if(category.toLowerCase().equals("tv")){
                    	TV newTV = new TV(name, price, quantity);
                    	inventory.addDevice(newTV);
                    	System.out.println(name + " added to inventory.");
                    }else if(category.toLowerCase().equals("laptop")){
                    	Laptop newLaptop = new Laptop(name, price, quantity);
                    	inventory.addDevice(newLaptop);
                    	System.out.println(name + " added to inventory.");
                    }else if(category.toLowerCase().equals("smart phone")){
                    	Smartphone newPhone = new Smartphone(name, price, quantity);
                    	inventory.addDevice(newPhone);
                    	System.out.println(name + " added to inventory.");
                    }else if(category.toLowerCase().equals("headphone")){
                    	Headphone newHeadphone = new Headphone(name, price, quantity);
                    	inventory.addDevice(newHeadphone);
                    	System.out.println(name + " added to inventory.");
                    }else if(category.toLowerCase().equals("smart watch")){
                    	SmartWatch newWatch = new SmartWatch(name, price, quantity);
                    	inventory.addDevice(newWatch);
                    	System.out.println(name + " added to inventory.");
                    }else System.out.println("There is no category named " + category);
                    break;

                case 2:
                    System.out.printf("Enter the name of the device to remove: ");
                    String deviceToRemove = scanner.nextLine();
                    inventory.removeDevice(deviceToRemove);
                    break;

                case 3:
                    System.out.printf("Enter the name of the device to update: ");
                    String deviceToUpdate = scanner.nextLine();
                    System.out.printf("Enter new price (leave blank to keep current price): ");
                    String newPriceStr = scanner.nextLine();
                    Double newPrice = newPriceStr.isEmpty() ? null : Double.parseDouble(newPriceStr);
                    System.out.printf("Enter new quantity (leave blank to keep current quantity): ");
                    String newQuantityStr = scanner.nextLine();
                    Integer newQuantity = newQuantityStr.isEmpty() ? null : Integer.parseInt(newQuantityStr);
                    inventory.updateDevice(deviceToUpdate, newPrice, newQuantity);
                    break;

                case 4:
                    System.out.println("Device List:");
                    inventory.displayDevices();
                    break;

                case 5:
                    inventory.findCheapestDevice();
                    break;

                case 6:
                    System.out.println("Devices sorted by price:");
                    inventory.sortDevicesByPrice();
                    break;

                case 7:
                    System.out.printf("Total inventory value: $%.2f%n", inventory.calculateTotalInventoryValue());
                    break;

                case 8:
                    System.out.println("Enter the name of the device to restock:");
                    String deviceToRestock = scanner.nextLine();
                    System.out.println("Do you want to add or remove stock? (Add/Remove):");
                    String restockAction = scanner.nextLine();
                    System.out.println("Enter the quantity to " + restockAction.toLowerCase() + ":");
                    int restockQuantity = scanner.nextInt();
                    boolean addStock = restockAction.equalsIgnoreCase("Add");
                    inventory.restockDevice(deviceToRestock, restockQuantity, addStock);
                    break;

                case 9:
                    inventory.exportInventoryReport();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
