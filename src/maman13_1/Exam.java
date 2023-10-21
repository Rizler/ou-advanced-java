package maman13_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Exam {
    /**
     * An Exam object represents a multiple choice exam, generated from a text file.
     * The exam is represented by a list of questions.
     * The exam can be answered by answering each question in order.
     */

    private ArrayList<Question> questions;
    private int nextQuestionIndex;
    private int correctAnswersCount;

    public Exam(String fileName) throws FileNotFoundException {
        // Constructs an Exam object from a text file.
        questions = new ArrayList<Question>();
        parseExamFile(fileName);
    }

    public Question getNextQuestion() {
        /*
          Returns the next question in the exam.
          If there are no more questions, returns null.
         */
        if (nextQuestionIndex >= questions.size()) {
            return null;
        }
        return questions.get(nextQuestionIndex);
    }

    public void answerQuestion(String answer) {
        /*
          Answers the next question in the exam.
          If there are no more questions, does nothing.
          If the answer is correct, increments the correct answers count.
         */
        if (nextQuestionIndex >= questions.size()) {
            return;
        }
        if (answer.equals(questions.get(nextQuestionIndex).getCorrectAnswer())) {
            correctAnswersCount += 1;
        }
        nextQuestionIndex += 1;
    }

    public boolean isCompleted() {
        // Returns true if all questions in the exam were answered.
        return nextQuestionIndex >= questions.size();
    }

    public int getGrade() {
        // Returns the grade of the exam, as a percentage.
        if (questions.isEmpty()) {
            return 0;
        }
        return (int) ((float) (correctAnswersCount) / questions.size() * 100);
    }

    private void parseExamFile(String fileName) throws FileNotFoundException {
        // Parses the exam file and populates the questions list.
        URL fileUrl = getClass().getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException(fileName);
        }
        Scanner input = new Scanner(new File(fileUrl.getPath()));
        while (input.hasNextLine()) {
            String question = input.nextLine();
            String correctAnswer = input.nextLine();
            String[] answers = new String[4];
            answers[0] = correctAnswer;
            for (int i = 1; i < 4; i++) {
                answers[i] = input.nextLine();
            }
            questions.add(new Question(question, correctAnswer, answers));
        }
        input.close();
    }
}
