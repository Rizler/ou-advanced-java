package maman13_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Exam {
    private ArrayList<Question> questions;
    private int nextQuestionIndex;
    private int correctAnswersCount;

    public Exam(String fileName) throws FileNotFoundException {
        questions = new ArrayList<Question>();
        parseExamFile(fileName);
    }

    public Question getNextQuestion() {
        if (nextQuestionIndex >= questions.size()) {
            return null;
        }
        return questions.get(nextQuestionIndex);
    }

    public void answerQuestion(String answer) {
        if (nextQuestionIndex >= questions.size()) {
            return;
        }
        if (answer.equals(questions.get(nextQuestionIndex).getCorrectAnswer())) {
            correctAnswersCount += 1;
        }
        nextQuestionIndex += 1;
    }

    public void answerQuestion(int answerIndex) {
        answerQuestion(questions.get(nextQuestionIndex).getAnswers().get(answerIndex - 1));
    }

    public boolean isCompleted() {
        return nextQuestionIndex >= questions.size();
    }

    public int getGrade() {
        if (questions.isEmpty()) {
            return 0;
        }
        return (int) ((float) (correctAnswersCount) / questions.size() * 100);
    }

    private void parseExamFile(String fileName) throws FileNotFoundException {
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

    public static void main(String[] args) {
        try {
            Exam exam = new Exam("exam.txt");
            Question question;
            Scanner input = new Scanner(System.in);
            while ((question = exam.getNextQuestion()) != null) {
                System.out.println(question.getQuestion());
                ArrayList<String> answers = question.getAnswers();
                for (int i = 0; i < answers.size(); i++) {
                    System.out.println((i + 1) + ". " + answers.get(i));
                }
                System.out.print("Enter your answer: ");
                exam.answerQuestion(input.nextInt());
            }
            input.close();
            System.out.println("Your grade is: " + exam.getGrade());
        } catch (FileNotFoundException e) {
            System.out.println("Exam file not found");
        }
    }
}
