/**
 * The TV class represents a specific type of electronic device: TV.
 * It implements the Device interface and provides methods to get and set its attributes.
 */
public class TV implements Device {
    
    private String category = "TV";
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new TV object with the specified name, price, and quantity.
     *
     * @param name     the name of the TV
     * @param price    the price of the TV
     * @param quantity the quantity of the TV
     */
    public TV(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @return the category of the TV
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the name of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @return the name of the TV
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @return the price of the TV
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @return the quantity of the TV
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @param price the new price of the TV
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @param quantity the new quantity of the TV
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the name of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @param name the new name of the TV
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the TV.
     * 
     * Time Complexity: O(1)
     *
     * @return a string representation of the TV
     */
    @Override
    public String toString() {
        return "Category: " + category + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

