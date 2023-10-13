package maman11_1;



public class Word {
    /**
     * A class representing a word in the game.
     */
    private String originalWord;
    private StringBuilder hiddenWord;

    public Word(String originalWord) {
        // Initialize the hidden word to be the same length as the original word, but with all letters replaced with '_'
        this.originalWord = originalWord.toLowerCase();
        hiddenWord = new StringBuilder();
        for (int i = 0; i < originalWord.length(); i++) {
            hiddenWord.append('_');
        }
    }

    public void guess(char letter) {
        // Replace all occurrences of the guessed letter in the hidden word with real letter.
        for (int i = 0; i < originalWord.length(); i++) {
            if (originalWord.charAt(i) == letter) {
                hiddenWord.setCharAt(i, letter);
            }
        }
    }

    public boolean isComplete() {
        // Check if the hidden word is the same as the original word.
        return hiddenWord.toString().equals(originalWord);
    }

    @Override
    public String toString() {
        // Return the hidden word as a string
        return hiddenWord.toString();
    }
}
