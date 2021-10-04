package ru.otus.spring.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.test.questions.questions.Question;
import ru.otus.spring.test.questions.questions.QuestionsService;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class QuestionsTestService implements TestService{
    private QuestionsService questionsService;
    private int score;

    ConsoleUtil consoleUtil;

    public QuestionsTestService() {
    }

    @Autowired
    public QuestionsTestService(QuestionsService questionsService, ConsoleUtil consoleUtil) {
        this.questionsService = questionsService;
        this.consoleUtil = consoleUtil;
    }

    @Override
    public int test() {
        for (Question question : questionsService.getQuestions()) {
            String answer = consoleUtil.ask(question.toString() + "Enter your answer(number)");

            if (question.isCorrectAnswer(question.getAnswers().get(Integer.parseInt(answer) - 1))) {
                score++;
            }
        }
        return score;
    }

    @Override
    public String printTestResult() {
        return "Your score: " + score + "/" + questionsService.getQuestions().size();
    }
}
