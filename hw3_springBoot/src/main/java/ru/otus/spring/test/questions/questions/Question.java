package ru.otus.spring.test.questions.questions;

import org.springframework.context.MessageSource;
import ru.otus.spring.util.outputUtil.ConsoleUtil;
import ru.otus.spring.util.outputUtil.OutputService;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> answers;
    private final String correctAnswer;

    public Question(List<String> record) {
        this.question = record.get(0);
        this.answers = record.subList(1, record.size() - 1);
        this.correctAnswer = record.get(record.size() - 1);
    }

    public String getAnswer(int index) throws WrongAnswerNumber{
        try {
            return answers.get(index - 1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new WrongAnswerNumber();
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