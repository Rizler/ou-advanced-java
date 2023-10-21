package maman13_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Question {
    // Represents a multiple choice question.
    private String question;
    private String correctAnswer;
    private ArrayList<String> answers;

    public Question(String question, String correctAnswer, String[] answers) {
        // Constructs a Question object. Randomizes the order of the answers.
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = new ArrayList<String>(Arrays.asList(answers));
        Collections.shuffle(this.answers);
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
