package ru.otus.spring.util.consoleUtil;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConsoleUtil {
    private final BufferedReader reader;

    public ConsoleUtil() {
        reader = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public void print(String message) {
        System.out.println(message);
    }

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

    public Map<String, String> signUp() {
        Map<String, String> questionAnswerMap = new HashMap<>();
        questionAnswerMap.put("firstName", ask("your name:"));
        questionAnswerMap.put("lastName", ask("your surname:"));
        return questionAnswerMap;
    }
}
