import java.util.Scanner;
import java.util.Date;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * The Inventory class represents an electronic shop's inventory.
 * It provides methods to manage and manipulate the inventory of devices.
 */
public class Inventory {
    private LinkedList<ArrayList<Device>> inventoryList;

    /**
     * Constructs a new Inventory object with an empty inventory list.
     */
    public Inventory() {
        this.inventoryList = new LinkedList<>();
    }

    /**
     * Adds a device to the inventory.
     * 
     * Time Complexity: O(n)
     *
     * n: number of categories in inventoryList.
     *
     * @param device the device to be added
     */
    public void addDevice(Device device) {
        boolean added = false;
        for (ArrayList<Device> categoryList : inventoryList) {
            if (categoryList.get(0).getCategory().equals(device.getCategory())) {
                categoryList.add(device);
                added = true;
                break;
            }
        }
        if (!added) {
            if(device.getCategory().toLowerCase().equals("tv") || device.getCategory().toLowerCase().equals("laptop") || device.getCategory().toLowerCase().equals("smart phone") 
            || device.getCategory().toLowerCase().equals("headphone") || device.getCategory().toLowerCase().equals("smart watch")){
                ArrayList<Device> newList = new ArrayList<>();
                newList.add(device);
                inventoryList.add(newList);
            }
        }
    }

    /**
     * Removes a device from the inventory by its name.
     *
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     * @param deviceName the name of the device to be removed
     */
    public void removeDevice(String deviceName) {
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                if (device.getName().equals(deviceName)) {
                    categoryList.remove(device);
                    if (categoryList.isEmpty()) {
                        inventoryList.remove(categoryList);
                    }
                    return;
                }
            }
        }
        System.out.println("Device not found.");
    }

    /**
     * Updates the price and quantity of a device by its name.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     * @param deviceName  the name of the device to be updated
     * @param newPrice    the new price of the device
     * @param newQuantity the new quantity of the device
     */
    public void updateDevice(String deviceName, Double newPrice, Integer newQuantity) {
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                if (device.getName().equals(deviceName)) {
                    if (newPrice != null) {
                        device.setPrice(newPrice);
                    }
                    if (newQuantity != null) {
                        device.setQuantity(newQuantity);
                    }
                    return;
                }
            }
        }
        System.out.println("Device not found.");
    }

    /**
     * Displays all devices in the inventory.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     */
    public void displayDevices() {
        int counter = 1;
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                System.out.println(counter + ". " + device);
                counter++;
            }
        }
    }

    /**
     * Finds and displays the cheapest device in the inventory.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     */
    public void findCheapestDevice() {
        double minPrice = Double.MAX_VALUE;
        Device cheapestDevice = null;
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                if (device.getPrice() < minPrice) {
                    minPrice = device.getPrice();
                    cheapestDevice = device;
                }
            }
        }
        System.out.println("The cheapest device is: " + cheapestDevice);
    }

    /**
     * Sorts and displays all devices in the inventory by price.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     * Explanation:
     *
     * time complexity of addAll is O(m) and for loop iterates for every category which is O(n). this gives us O(n*m).
     *
     * time complexity of sort() is O(n*logn) which is better than O(n*m) so the worst time complexity is O(n*m).
     */
    public void sortDevicesByPrice() {
        ArrayList<Device> allDevices = new ArrayList<>(); 
        for (ArrayList<Device> categoryList : inventoryList) {
            allDevices.addAll(categoryList);
        }
        allDevices.sort(Comparator.comparingDouble(Device::getPrice));
        int counter = 1;
        for (Device device : allDevices) {
            System.out.println(counter + ". " + device);
            counter++;
        }
    }

    /**
     * Calculates and returns the total value of the inventory.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     * @return the total value of the inventory
     */
    public double calculateTotalInventoryValue() {
        double totalValue = 0;
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        return totalValue;
    }

    /**
     * Restocks a device by its name.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     * @param deviceName the name of the device to be restocked
     * @param quantity   the quantity to be restocked
     * @param add        true if adding stock, false if removing stock
     */
    public void restockDevice(String deviceName, int quantity, boolean add) {
        for (ArrayList<Device> categoryList : inventoryList) {
            for (Device device : categoryList) {
                if (device.getName().equals(deviceName)) {
                    if (add) {
                        device.setQuantity(device.getQuantity() + quantity);
                    } else {
                        device.setQuantity(device.getQuantity() - quantity);
                    }
                    return;
                }
            }
        }
        System.out.println("Device not found.");
    }

    /**
     * Exports the inventory report to a text file and displays it.
     * 
     * Time Complexity: O(n*m)
	 *
     * n: number of categories in inventoryList.
     *
     * m: maximum amount of devices a category stores.
     *
     */
    public void exportInventoryReport() {
        try {
            FileWriter fileWriter = new FileWriter("report.txt");
            fileWriter.write("Electronics Shop Inventory Report\n");
            fileWriter.write("Generated on: " + new Date().toString() + "\n");
            fileWriter.write("-----------------------------------------------------------------------\n");
            fileWriter.write("| No. | Category       | Name                  | Price     | Quantity |\n");
            fileWriter.write("-----------------------------------------------------------------------\n");
            int counter = 1;
            for (ArrayList<Device> categoryList : inventoryList) {
                for (Device device : categoryList) {
                    fileWriter.write(String.format("| %-4d| %-15s| %-22s| $%-9.2f| %-9d|\n", counter, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity()));
                    counter++;
                }
            }
            fileWriter.write("-----------------------------------------------------------------------\n");
            fileWriter.write("Summary:\n");
            fileWriter.write("- Total Number of Devices: " + (counter - 1) + "\n");
            fileWriter.write("- Total Inventory Value: $" + String.format("%.2f", calculateTotalInventoryValue()) + "\n");
    
            System.out.println("Report exported to report.txt successfully.");
    
            fileWriter.close();
    
            // Read and print the contents of report.txt using Scanner
            Scanner fileScanner = new Scanner(new File("report.txt"));
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to or reading from report.txt: " + e.getMessage());
        }
    }
}

