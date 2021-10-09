package ru.otus.spring.util.outputUtil;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ConsoleUtil implements OutputService {
    private final BufferedReader reader;
    private final MessageSource messageSource;

    public ConsoleUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
        reader = new BufferedReader(
                new InputStreamReader(System.in));
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String ask(String question) {

        System.out.println(question);

        String answer = null;
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public Map<String, String> signUp() {
        Map<String, String> questionAnswerMap = new HashMap<>();
        questionAnswerMap.put("firstName", ask(messageSource.getMessage("yourname", null, Locale.getDefault())));
        questionAnswerMap.put("lastName", ask(messageSource.getMessage("yoursurname", null, Locale.getDefault())));
        return questionAnswerMap;
    }
}
