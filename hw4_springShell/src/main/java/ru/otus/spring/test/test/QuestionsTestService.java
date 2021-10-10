package ru.otus.spring.test.test;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.test.questions.questions.Question;
import ru.otus.spring.test.questions.questions.QuestionsService;
import ru.otus.spring.test.questions.questions.WrongAnswerNumber;
import ru.otus.spring.util.outputUtil.OutputService;

@Component
public class QuestionsTestService implements TestService {
    private QuestionsService questionsService;
    private int score;

    private final OutputService outputService;
    private final MessageSource messageSource;

    public QuestionsTestService(QuestionsService questionsService, OutputService outputService, MessageSource messageSource) {
        this.questionsService = questionsService;
        this.outputService = outputService;
        this.messageSource = messageSource;
    }

    @Override
    public int test() {
        for (Question question : questionsService.getQuestions()) {
            String answer = null;
            while (true) {
                try {
                    answer = askQuestion(question);
                    break;
                } catch (WrongAnswerNumber wrongAnswerNumber) {
                    outputService.print(messageSource.getMessage("error.wrongAnswerNumber",
                            null, null));
                }
            }


            if (question.isCorrectAnswer(answer)) {
                score++;
            }
        }
        return score;
    }

    @Override
    public String printTestResult() {
        return messageSource.getMessage("result", null, null) + " " + score + "/" + questionsService.getQuestions().size();
    }


    private String askQuestion(Question question) throws WrongAnswerNumber {
        String answer = outputService.ask(question.toString() + messageSource.getMessage("youranswer",
                null, null));
        answer = question.getAnswer(Integer.parseInt(answer));
        return answer;
    }
}