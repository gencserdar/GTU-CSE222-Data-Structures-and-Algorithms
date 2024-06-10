import javax.swing.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The Main class is the entry point for the application, which processes commands from an input file and
 * visualizes the performance of different operations (ADD, REMOVE, SEARCH) on a stock data manager.
 */
public class Main {
    static int k = 0;

    /**
     * The main method to run the application. It reads commands from an input file, processes them,
     * performs performance analysis, and creates visualizations of the performance data.
     *
     * @param args Command line arguments. The first argument should be the path to the input file.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }
        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        List<Integer> dataPointsX= new ArrayList<>();
        List<Long> dataPointsY_add = new ArrayList<>();
        List<Long> dataPointsY_remove = new ArrayList<>();
        List<Long> dataPointsY_search = new ArrayList<>();
        int i = 0;
        int TEST_AMOUNT=100;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                i++;
                dataPointsX.add(i);
                processCommand(line, manager);
                performPerformanceAnalysis(manager, TEST_AMOUNT, dataPointsY_add, dataPointsY_remove, dataPointsY_search);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform a simple performance analysis

        // Create and display the visualization
        SwingUtilities.invokeLater(() -> {
            GUIVisualization visualization_add = new GUIVisualization("scatter",dataPointsX, dataPointsY_add, "Add");
            GUIVisualization visualization_remove = new GUIVisualization("scatter",dataPointsX, dataPointsY_remove, "Remove");
            GUIVisualization visualization_search = new GUIVisualization("scatter",dataPointsX, dataPointsY_search, "Search");
            visualization_add.setVisible(true);
            visualization_remove.setVisible(true);
            visualization_search.setVisible(true);
        });
    }

    /**
     * Processes a single command from the input file and applies it to the StockDataManager.
     *
     * @param line    The command line from the input file.
     * @param manager The StockDataManager instance to apply the command to.
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    /**
     * Performs a performance analysis of the StockDataManager by measuring the time taken for ADD, REMOVE,
     * and SEARCH operations, and stores the results in the provided lists.
     *
     * @param manager          The StockDataManager instance to analyze.
     * @param size             The number of operations to perform for each test.
     * @param dataPointsY_add  The list to store performance data for ADD operations.
     * @param dataPointsY_remove The list to store performance data for REMOVE operations.
     * @param dataPointsY_search The list to store performance data for SEARCH operations.
     */
    private static void performPerformanceAnalysis(StockDataManager manager, int size, List<Long> dataPointsY_add, List<Long> dataPointsY_remove, List<Long> dataPointsY_search) {
        long startTime, endTime;
                long startTime1, endTime1;

        // Measure time for ADD operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        dataPointsY_add.add((endTime - startTime)/size);

        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        dataPointsY_search.add((endTime - startTime)/size);

        // Measure time for REMOVE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.removeStock("SYM" + i);
        }
        endTime = System.nanoTime();
        dataPointsY_remove.add((endTime - startTime)/size);
    }
}
