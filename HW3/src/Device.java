/**
 * The Device interface represents an electronic device.
 * It defines methods to get and set the category, name, price, and quantity of the device.
 */
interface Device {
    String getCategory();
    String getName();
    double getPrice();
    int getQuantity();
    void setPrice(double price);
    void setQuantity(int quantity);
    void setName(String name);
}
