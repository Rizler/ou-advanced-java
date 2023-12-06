package maman11_1;

public class WordGenerator {
    /**
     * A list of words to be used in the game.
     */
    private static final String[] WORDS = {"hello", "world", "java", "programming", "computer", "science", "technology",
            "software", "hardware", "network", "internet", "application", "development", "testing", "debugging",
            "maintenance", "security", "database", "algorithm", "data", "structure", "information", "system"};

    public static Word generateWord() {
        // Generate a random word from the list of words.
        return new Word(WORDS[(int) (Math.random() * WORDS.length)]);
    }
}
