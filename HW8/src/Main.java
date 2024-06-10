import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main class for running the Social Network Analysis program.
 */
public class Main {
    /**
     * Main method to start the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph(); // Initialize the social network graph
        Scanner scanner = new Scanner(System.in); // Initialize scanner for user input
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Date format for timestamp
        Date defaultTimestamp = new Date(); // Default timestamp for demonstration

        // Adding some people for demonstration
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"), defaultTimestamp);
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"), defaultTimestamp);
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"), defaultTimestamp);
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"), defaultTimestamp);
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"), defaultTimestamp);
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"), defaultTimestamp);

        // Counting clusters for demonstration
        network.countClusters();

        // Main program loop
        while (true) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int option = Integer.parseInt(scanner.nextLine()); // Get user input for menu option

            // Perform actions based on user's choice
            switch (option) {
                case 1:
                    // Add person
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter hobbies (comma-separated): ");
                    List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));
                    Date timestamp = new Date();
                    network.addPerson(name, age, hobbies, timestamp);
                    break;
                case 2:
                    // Remove person
                    try {
                        System.out.print("Enter name: ");
                        String removeName = scanner.nextLine();
                        System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date removeTimestamp = dateFormat.parse(scanner.nextLine());
                        network.removePerson(removeName, removeTimestamp);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                    }
                    break;
                case 3:
                    // Add friendship
                    try {
                        System.out.print("Enter first person's name: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date timestamp1 = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter second person's name: ");
                        String name2 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date timestamp2 = dateFormat.parse(scanner.nextLine());
                        network.addFriendship(name1, timestamp1, name2, timestamp2);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                    }
                    break;
                case 4:
                    // Remove friendship
                    try {
                        System.out.print("Enter first person's name: ");
                        String name3 = scanner.nextLine();
                        System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date timestamp3 = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter second person's name: ");
                        String name4 = scanner.nextLine();
                        System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date timestamp4 = dateFormat.parse(scanner.nextLine());
                        network.removeFriendship(name3, timestamp3, name4, timestamp4);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                    }
                    break;
                case 5:
                    // Find shortest path
                    try {
                        System.out.print("Enter start person's name: ");
                        String startName = scanner.nextLine();
                        System.out.print("Enter start person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date startTimestamp = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter end person's name: ");
                        String endName = scanner.nextLine();
                        System.out.print("Enter end person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date endTimestamp = dateFormat.parse(scanner.nextLine());
                        network.findShortestPath(startName, startTimestamp, endName, endTimestamp);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                    }
                    break;
                case 6:
                    // Suggest friends
                    try {
                        System.out.print("Enter person's name: ");
                        String suggestName = scanner.nextLine();
                        System.out.print("Enter person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                        Date suggestTimestamp = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter maximum number of friends to suggest: ");
                        int maxSuggestions = Integer.parseInt(scanner.nextLine());
                        network.suggestFriends(suggestName, suggestTimestamp, maxSuggestions);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                    }
                    break;
                case 7:
                    // Count clusters
                    network.countClusters();
                    break;
                case 8:
                    // Exit program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    // Invalid option
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}