package maman11_1;

import java.util.HashSet;
import java.util.Scanner;

public class WordGuessingGame {
    /**
     * Logic for the word guessing game.
     */
    private boolean isValidGuess(String input, HashSet<Character> previousGuesses) {
        // Check if the input is a valid guess.
        if (input.length() != 1) {
            System.out.println("Please enter a single letter!");
            return false;
        }
        if (!Character.isLetter(input.charAt(0))) {
            System.out.println("Please enter a letter!");
            return false;
        }
        if (previousGuesses.contains(input.charAt(0))) {
            System.out.println("You already guessed this letter!");
            return false;
        }
        return true;
    }

    private void playGame() {
        // Play a single game of word guessing.
        Scanner scanner = new Scanner(System.in);
        Word word = WordGenerator.generateWord();
        System.out.println("The word is: " + word);
        HashSet<Character> previousGuesses = new HashSet<Character>();
        while (!word.isComplete()) {
            System.out.print("Please enter a letter to guess: ");
            String input = scanner.next().toLowerCase();
            if (!isValidGuess(input, previousGuesses)) {
                continue;
            }
            word.guess(input.charAt(0));
            System.out.println("The word is: " + word);
            previousGuesses.add(input.charAt(0));
        }
        System.out.println("You guessed the word in " + previousGuesses.size() + " guesses!");
    }

    public static void main(String[] args) {
        WordGuessingGame game = new WordGuessingGame();
        System.out.println("Welcome to the word guessing game!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            game.playGame();
            while (true) {
                System.out.print("Would you like to play again? (y/n) ");
                String answer = scanner.next();
                if (answer.equals("y")) {
                    break;
                } else if (answer.equals("n")) {
                    System.out.println("Thanks for playing!");
                    return;
                }
            }
        }
    }
}