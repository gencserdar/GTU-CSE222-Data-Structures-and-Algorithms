import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a person in the social network.
 */
public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a Person object with the given attributes.
     *
     * @param name      the name of the person
     * @param age       the age of the person
     * @param hobbies   the hobbies of the person
     * @param timestamp the timestamp of when the person was added to the network
     */
    public Person(String name, int age, List<String> hobbies, Date timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = timestamp;
    }

    /**
     * Returns the formatted timestamp of the person.
     *
     * @return the formatted timestamp
     */
    public String getFormattedTimestamp() {
        return dateFormat.format(timestamp);
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ", Timestamp: " + getFormattedTimestamp() + ")";
    }
}