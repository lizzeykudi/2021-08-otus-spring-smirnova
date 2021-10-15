package test;

import questions.Question;
import questions.Questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    private Questions questions;
    private int score;


    public void test() {
        for (Question question : questions.getQuestions()) {
            System.out.println(question.toString());
            System.out.println("Enter your answer(number)");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String answer = null;
            try {
                answer = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (question.isCorrectAnswer(question.getAnswers().get(Integer.parseInt(answer) - 1))) {
                score++;
            }
        }
        System.out.println("Your score: " + score + "/" + questions.getQuestions().size());
    }
}
