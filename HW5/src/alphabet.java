import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
    // Set to store the English alphabet in order
    private Set<Character> english_alphabet = new LinkedHashSet<Character>();
    // Map to store the Vigenere cipher mapping
    private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
    
    public alphabet() {
        // Method to initialize the English alphabet and fill the map
        fill_english_alphabet();
        fill_map();
    }
    
    // Method to fill the English alphabet set
    private void fill_english_alphabet() {
        for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            english_alphabet.add(c);
        }
    }
    
    // Method to fill the Vigenere cipher map
    private void fill_map() {
        // Iterator to iterate through the English alphabet set for rows
        Iterator<Character> rowIterator = english_alphabet.iterator();

        while (rowIterator.hasNext()) {
            char rowIndicator = rowIterator.next();
            // Iterator to iterate through the English alphabet set for columns
            Iterator<Character> columnIterator = english_alphabet.iterator();
            // Inner map to store the mapping for each row
            Map<Character, Character> innerMap = new HashMap<>();

            while (columnIterator.hasNext()) {
                char columnIndicator = columnIterator.next();
                // Calculate the Vigenere cipher character based on row and column indicators
                char ch = (char) ('A' + (columnIndicator - 'A' + rowIndicator - 'A') % 26);
                innerMap.put(columnIndicator, ch); // Add the mapping to the inner map
            }

            map.put(rowIndicator, innerMap); // Add the inner map to the main map
        }
    }

    // Method to print the Vigenere cipher map
    public void print_map() {
        System.out.println("*** Viegenere Cipher ***\n\n");
        System.out.println("    " + english_alphabet);
        System.out.print("    ------------------------------------------------------------------------------");
        for(Character k: map.keySet()) {
            System.out.print("\n" + k + " | ");
            System.out.print(map.get(k).values());
        }
        System.out.println("\n");
    }

    // Getter method to retrieve the Vigenere cipher map
    public Map get_map() {
        return map;
    }
}
