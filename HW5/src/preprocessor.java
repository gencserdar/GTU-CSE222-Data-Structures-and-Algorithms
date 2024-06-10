public class preprocessor {
    private String initial_string;
    private String preprocessed_string;
        
    // Constructor to initialize the object with the initial string
    public preprocessor(String str) {
        this.initial_string = str;
        this.preprocessed_string ="";
    }

    // Method to preprocess the initial string
    public void preprocess() {
        capitalize();
        clean();
    }
    
    // Method to capitalize the initial string
    private void capitalize() {
        for (int i = 0; i < initial_string.length(); i++) {
            char ch = initial_string.charAt(i);
            // Turkish letter 'ı' transforms into uppercase as 'I' which is an english letter so we exclude it from being transformed into uppercase
            if (ch != 'ı') preprocessed_string += Character.toUpperCase(ch);
        }
    }


    // Method to clean the preprocessed string by removing non-alphabetic characters
	//
	// NOTE: In my windows compiler when I write Ş it reads as S and also when I write Ğ it reads as G. Because of that Ş and Ğ are not seen as invalid-characters.
	// But in my ubuntu compiler it works normal, we base on ubuntu terminal so I am not changing anything in my code for it.
    //
    
    private void clean() {
        // Remove non-alphabetic characters using regular expression
        preprocessed_string = preprocessed_string.replaceAll("[^A-Za-z]", "");
        // Check if the resulting string is empty
        if (preprocessed_string.isEmpty()) {
            // Print a message and exit the program if the string is empty
            System.out.println("Given input is not proper. Please try again.");
            System.exit(1);
        }
    }
    
    // Method to retrieve the preprocessed string
    public String get_preprocessed_string() {
        return preprocessed_string;
    }
}
