import java.util.Map;
import java.util.Iterator;

public class decryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text = "";
    private String cipher_text;
    
    // Constructor to initialize the decryptor
    public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        this.map = _map;
        this.key = _key;
        this.cipher_text = text;
    }

    // Method to perform decryption
    public void decrypt() {
        generate_keystream();
        generate_plain_text();
    }
    
    // Method to generate the keystream
    private void generate_keystream() {
        int text_len = cipher_text.length();
        int key_len = key.length();
        // Generate the keystream based on the length of the text and key
        if (text_len < key_len) 
            keystream = key.substring(0, text_len);
        else if (text_len > key_len) {
            while (keystream.length() < text_len) 
                keystream += key;
            keystream = keystream.substring(0, text_len);
        } else 
            keystream = key;
    }
    
    // Method to generate the plaintext
    private void generate_plain_text() {
        // Iterator for the keystream and ciphertext
        Iterator<Character> keystreamIterator = keystream.chars().mapToObj(c -> (char) c).iterator();
        Iterator<Character> cipherIterator = cipher_text.chars().mapToObj(c -> (char) c).iterator();

        while (keystreamIterator.hasNext() && cipherIterator.hasNext()) {
            char keystream_char = keystreamIterator.next();
            char cipher_char = cipherIterator.next();

            // Iterate through the key set of the map
            Iterator<Character> keySetIterator = map.get(keystream_char).keySet().iterator();
            Character plain_char = ' ';
            while (keySetIterator.hasNext()) {
                Character column_char = keySetIterator.next();
                // Check if the cipher character matches the mapping
                if (map.get(keystream_char).get(column_char).equals(cipher_char)) {
                    plain_char = column_char;
                    break;
                }
            }
            // Append the plaintext character to the plaintext
            plain_text += plain_char;
        }
    }

    // Getter method to retrieve the keystream
    public String get_keystream() {
        return keystream;    
    }
    
    // Getter method to retrieve the plaintext
    public String get_plain_text() {
        return plain_text;
    }
}
