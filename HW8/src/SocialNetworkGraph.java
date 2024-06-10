import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a social network graph.
 */
public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Adds a person to the social network.
     *
     * @param name      the name of the person
     * @param age       the age of the person
     * @param hobbies   the hobbies of the person
     * @param timestamp the timestamp of when the person is added
     */
    public void addPerson(String name, int age, List<String> hobbies, Date timestamp) {
        Person person = new Person(name, age, hobbies, timestamp);
        people.put(name + dateFormat.format(timestamp), person); // Use name and formatted timestamp as key
        friendships.put(person, new ArrayList<>());
        System.out.println("Person is added: " + person);
    }

    /**
     * Removes a person from the social network.
     *
     * @param name      the name of the person
     * @param timestamp the timestamp of the person to remove
     */
    public void removePerson(String name, Date timestamp) {
        String user = name + dateFormat.format(timestamp);
        Person person = people.remove(user);
        if (person != null) {
            friendships.remove(person);
            for (List<Person> friends : friendships.values()) friends.remove(person);
            System.out.println("Person is removed: " + person);
        } else System.out.println("Person not found.");
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param name1     the name of the first person
     * @param timestamp1 the timestamp of the first person
     * @param name2     the name of the second person
     * @param timestamp2 the timestamp of the second person
     */
    public void addFriendship(String name1, Date timestamp1, String name2, Date timestamp2) {
        Person person1 = people.get(name1 + dateFormat.format(timestamp1));
        Person person2 = people.get(name2 + dateFormat.format(timestamp2));
        if (person1 != null && person2 != null) {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.name + " and " + person2.name);
        } 
        else if(person1==null && person2==null) System.out.println("Users not found: " + name1 + ", " + name2);
        else if(person1==null) System.out.println("User not found: " + name1);
        else if(person2==null) System.out.println("User not found: " + name2);
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1     the name of the first person
     * @param timestamp1 the timestamp of the first person
     * @param name2     the name of the second person
     * @param timestamp2 the timestamp of the second person
     */
    public void removeFriendship(String name1, Date timestamp1, String name2, Date timestamp2) {
        Person person1 = people.get(name1 + dateFormat.format(timestamp1));
        Person person2 = people.get(name2 + dateFormat.format(timestamp2));
        if (person1 != null && person2 != null) {
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
        }
        else if(person1==null && person2==null) System.out.println("Users not found: " + name1 + ", " + name2);
        else if(person1==null) System.out.println("User not found: " + name1);
        else if(person2==null) System.out.println("User not found: " + name2);
    }

    /**
     * Finds the shortest path between two people in the social network.
     *
     * @param name1     the name of the start person
     * @param timestamp1 the timestamp of the start person
     * @param name2     the name of the end person
     * @param timestamp2 the timestamp of the end person
     */
    public void findShortestPath(String name1, Date timestamp1, String name2, Date timestamp2) {
        Person startUser = people.get(name1 + dateFormat.format(timestamp1));
        Person endUser = people.get(name2 + dateFormat.format(timestamp2));
        if (startUser == null || endUser == null) {
            System.out.println("Users not found.");
            return;
        }
    
        // BFS implementation
        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> prevUser = new HashMap<>();
        Set<Person> visited = new HashSet<>();
        queue.add(startUser);
        visited.add(startUser);
    
        while (!queue.isEmpty()) {
            Person currentUser = queue.poll();
            for (Person neighbor : friendships.get(currentUser)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prevUser.put(neighbor, currentUser);
                    if (neighbor.equals(endUser)) {
                        printPath(startUser, endUser, prevUser);
                        return;
                    }
                }
            }
        }
        System.out.println("No path found between " + name1 + " and " + name2);
    }

    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) path.add(at);
        Collections.reverse(path);
        System.out.println("Shortest path: " + path);
    }

    /**
     * Counts the number of clusters in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;

        for (Person person : people.values()) {
            if (!visited.contains(person)) {
                clusterCount++;
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                System.out.println("Cluster " + clusterCount + ": " + cluster);
            }
        }
        System.out.println("Number of clusters found: " + clusterCount);
    }

    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);
            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    /**
     * Suggests friends to a person based on mutual friends and common hobbies.
     *
     * @param name           the name of the person
     * @param timestamp      the timestamp of the person
     * @param maxSuggestions the maximum number of friend suggestions
     */
    public void suggestFriends(String name, Date timestamp, int maxSuggestions) {
        Person person = people.get(name + dateFormat.format(timestamp));
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }

        Map<Person, Double> scores = new HashMap<>();

        for (Person p : people.values()) {
            if (!p.equals(person) && !friendships.get(person).contains(p)) {
                int mutualFriends = 0, commonHobbies = 0;
                for (Person friend : friendships.get(person)) if (friendships.get(p).contains(friend)) mutualFriends++;
                for (String hobby : person.hobbies) if (p.hobbies.contains(hobby)) commonHobbies++;
                double score = mutualFriends + 0.5 * commonHobbies;
                scores.put(p, score);
            }
        }

        scores.entrySet().stream().sorted(Map.Entry.<Person, Double>comparingByValue().reversed()).limit(maxSuggestions).forEach(entry -> {
            Person suggestedPerson = entry.getKey();
            double score = entry.getValue();
            System.out.println(suggestedPerson.name + " (Score: " + score + ")");
        });
    }
}