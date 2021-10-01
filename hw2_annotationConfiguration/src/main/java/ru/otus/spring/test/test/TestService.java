package ru.otus.spring.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.test.questions.questions.Question;
import ru.otus.spring.test.questions.questions.QuestionsService;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class TestService {
    private QuestionsService questionsService;
    private int score;

    ConsoleUtil consoleUtil;

    public TestService() {
    }

    @Autowired
    public TestService(QuestionsService questionsService, ConsoleUtil consoleUtil) {
        this.questionsService = questionsService;
        this.consoleUtil = consoleUtil;
    }

    public int test() {
        for (Question question : questionsService.getQuestions()) {
            String answer = consoleUtil.ask(question.toString() + "Enter your answer(number)");

            if (question.isCorrectAnswer(question.getAnswers().get(Integer.parseInt(answer) - 1))) {
                score++;
            }
        }
        return score;
    }

    public String printTestResult() {
        return "Your score: " + score + "/" + questionsService.getQuestions().size();
    }
}
