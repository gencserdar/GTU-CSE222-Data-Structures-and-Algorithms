import java.util.Map;

public class encryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text;
    private String cipher_text = "";
    
    // Constructor to initialize the encryptor
    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        this.map = _map;
        this.key = _key;
        this.plain_text = text;
    }
    
    // Method to perform encryption
    public void encrypt() {
        generate_keystream();
        generate_cipher_text();
    }
    
    // Method to generate the keystream
    private void generate_keystream() {
        int text_len = plain_text.length();
        int key_len = key.length();
        // Generate the keystream based on the length of the plaintext and key
        if (text_len < key_len) 
            keystream = key.substring(0, text_len);
        else if (text_len > key_len) {
            while (keystream.length() < text_len) 
                keystream += key;
            keystream = keystream.substring(0, text_len);
        } else 
            keystream = key;
    }
    
    // Method to generate the ciphertext
    private void generate_cipher_text() {
        for (int i = 0; i < plain_text.length(); i++) {
            char key_char = keystream.charAt(i);
            char plain_char = plain_text.charAt(i);
            // Encrypt each character of the plaintext
            char cipher_char = map.get(plain_char).get(key_char);
            cipher_text += cipher_char;
        }
    }

    // Getter method to retrieve the keystream
    public String get_keystream() {
        return keystream;
    }
    
    // Getter method to retrieve the ciphertext
    public String get_cipher_text() {
        return cipher_text;
    }
}
