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
            String answer = askQuestion(question);
            while (answer==null) {
                answer = askQuestion(question);
            }
            if (question.isCorrectAnswer(answer)) {
                score++;
            }
        }
        return score;
    }

    private String askQuestion(Question question){
        String answer = consoleUtil.ask(question.toString() + "Enter your answer(number)");
        answer = question.getAnswer(Integer.parseInt(answer));
        return answer;
    }

    @Override
    public String printTestResult() {
        return "Your score: " + score + "/" + questionsService.getQuestions().size();
    }
}

