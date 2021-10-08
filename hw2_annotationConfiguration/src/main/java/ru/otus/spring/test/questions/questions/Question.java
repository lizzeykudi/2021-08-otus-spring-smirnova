package ru.otus.spring.test.questions.questions;

import ru.otus.spring.util.consoleUtil.ConsoleUtil;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> answers;
    private final String correctAnswer;
    private final ConsoleUtil consoleUtil;

    public Question(List<String> record) {
        this.question = record.get(0);
        this.answers = record.subList(1, record.size() - 1);
        this.correctAnswer = record.get(record.size() - 1);
        this.consoleUtil = new ConsoleUtil();
    }

    public String getAnswer(int index) {
        try {
            return answers.get(index - 1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            consoleUtil.print("No answer with number "+index);
            return null;
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswer(String answer) {
        if (correctAnswer.equals(answer)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String questionString = question + "\n";
        for (int i = 0; i < answers.size(); i++) {
            questionString += (i + 1 + ")" + answers.get(i) + "\n");
        }
        return questionString;
    }
}
