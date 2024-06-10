/**
 * The StockDataManager class manages a collection of stocks using an AVL tree.
 */
public class StockDataManager {
    private AVLTree avlTree;

    /**
     * Constructs a StockDataManager and initializes the AVL tree.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Adds a new stock or updates an existing stock with the given details.
     *
     * @param symbol    the symbol of the stock
     * @param price     the price of the stock
     * @param volume    the volume of the stock
     * @param marketCap the market capitalization of the stock
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }

    /**
     * Removes the stock with the given symbol.
     *
     * @param symbol the symbol of the stock to be removed
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock with the given symbol.
     *
     * @param symbol the symbol of the stock to be searched
     * @return the stock with the given symbol, or null if not found
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    /**
     * Updates the details of a stock with the given symbol.
     *
     * @param symbol       the symbol of the stock to be updated
     * @param newSymbol    the new symbol of the stock
     * @param newPrice     the new price of the stock
     * @param newVolume    the new volume of the stock
     * @param newMarketCap the new market capitalization of the stock
     */
    public void updateStock(String symbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            stock.setSymbol(newSymbol);
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }

    /**
     * The main method for testing the StockDataManager class.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        StockDataManager manager = new StockDataManager();
        manager.addOrUpdateStock("AAPL", 150.0, 1000000, 2500000000L);
        manager.addOrUpdateStock("GOOGL", 2800.0, 500000, 1500000000L);
        System.out.println(manager.searchStock("AAPL"));
        manager.removeStock("AAPL");
        System.out.println(manager.searchStock("AAPL"));
    }
}
