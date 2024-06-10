import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**
 * A utility class to generate an input file with random commands for testing.
 * The commands include ADD, REMOVE, SEARCH, and UPDATE operations on stock symbols.
 */
public class InputFileGenerator {

    /**
     * The main method to generate the input file.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        int addCount = 2000;
        int removeCount = 3000;
        int searchCount = 1400;
        int updateCount = 200;
        String filename = "input.txt";

        try {
            generateInputFile(addCount, removeCount, searchCount, updateCount, filename);
            System.out.println("Input file generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating input file: " + e.getMessage());
        }
    }

    /**
     * Generates an input file with the specified number of ADD, REMOVE, SEARCH, and UPDATE commands.
     * 
     * @param addCount    The number of ADD commands to generate.
     * @param removeCount The number of REMOVE commands to generate.
     * @param searchCount The number of SEARCH commands to generate.
     * @param updateCount The number of UPDATE commands to generate.
     * @param filename    The name of the file to generate.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public static void generateInputFile(int addCount, int removeCount, int searchCount, int updateCount, String filename) throws IOException {
        Random random = new Random();
        FileWriter writer = new FileWriter(filename);


        // Generate ADD commands
        for (int i = 0; i < addCount; i++) {
            String symbol = generateRandomSymbol(random);
            double price = generateRandomPrice(random);
            long volume = generateRandomVolume(random);
            long marketCap = generateRandomMarketCap(random);
            writer.write(String.format("ADD %s %.2f %d %d\n", symbol, price, volume, marketCap));
        }

        // Generate REMOVE commands
        for (int i = 0; i < removeCount; i++) {
            String symbol = generateRandomSymbol(random);
            writer.write(String.format("REMOVE %s\n", symbol));
        }

        // Generate SEARCH commands
        for (int i = 0; i < searchCount; i++) {
            String symbol = generateRandomSymbol(random);
            writer.write(String.format("SEARCH %s\n", symbol));
        }

        // Generate UPDATE commands
        for (int i = 0; i < updateCount; i++) {
            String oldSymbol = generateRandomSymbol(random);
            String newSymbol = generateRandomSymbol(random);
            double price = generateRandomPrice(random);
            long volume = generateRandomVolume(random);
            long marketCap = generateRandomMarketCap(random);
            writer.write(String.format("UPDATE %s %s %.2f %d %d\n", oldSymbol, newSymbol, price, volume, marketCap));
        }

        writer.close();
    }

    /**
     * Generates a random stock symbol.
     * 
     * @param random The Random instance used to generate the symbol.
     * @return A random stock symbol consisting of 1 to 3 uppercase letters.
     */
    private static String generateRandomSymbol(Random random) {
        int length = random.nextInt(3) + 1;
        StringBuilder symbol = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            symbol.append((char) ('A' + random.nextInt(26)));
        }
        return symbol.toString();
    }

    /**
     * Generates a random stock price.
     * 
     * @param random The Random instance used to generate the price.
     * @return A random stock price between 10 and 1000.
     */
    private static double generateRandomPrice(Random random) {
        return 10 + (1000 - 10) * random.nextDouble();
    }

    /**
     * Generates a random stock volume.
     * 
     * @param random The Random instance used to generate the volume.
     * @return A random stock volume between 100 and 10000.
     */
    private static long generateRandomVolume(Random random) {
        return 100 + random.nextInt(10000);
    }

    /**
     * Generates a random market capitalization.
     * 
     * @param random The Random instance used to generate the market capitalization.
     * @return A random market capitalization between 1,000,000 and 1,000,000,000.
     */
    private static long generateRandomMarketCap(Random random) {
        long minMarketCap = 1_000_000L;
        long maxMarketCap = 1_000_000_000L;
        return minMarketCap + (long) (random.nextDouble() * (maxMarketCap - minMarketCap));
    }
}
