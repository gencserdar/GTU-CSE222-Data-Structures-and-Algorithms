/**
 * The Stock class represents a stock with a symbol, price, volume, and market capitalization.
 */
public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    /**
     * Constructs a Stock with the specified symbol, price, volume, and market capitalization.
     *
     * @param symbol    the symbol of the stock
     * @param price     the price of the stock
     * @param volume    the volume of the stock
     * @param marketCap the market capitalization of the stock
     */
    public Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Gets the symbol of the stock.
     *
     * @return the symbol of the stock
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol of the stock.
     *
     * @param symbol the new symbol of the stock
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the price of the stock.
     *
     * @return the price of the stock
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the stock.
     *
     * @param price the new price of the stock
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the volume of the stock.
     *
     * @return the volume of the stock
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the stock.
     *
     * @param volume the new volume of the stock
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Gets the market capitalization of the stock.
     *
     * @return the market capitalization of the stock
     */
    public long getMarketCap() {
        return marketCap;
    }

    /**
     * Sets the market capitalization of the stock.
     *
     * @param marketCap the new market capitalization of the stock
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the stock.
     *
     * @return a string representation of the stock
     */
    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}
