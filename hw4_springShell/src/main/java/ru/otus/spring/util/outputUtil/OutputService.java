package ru.otus.spring.util.outputUtil;

import java.util.Map;

public interface OutputService {

    public void print(String message);

    public String ask(String question);

    public Map<String, String> signUp();
}
