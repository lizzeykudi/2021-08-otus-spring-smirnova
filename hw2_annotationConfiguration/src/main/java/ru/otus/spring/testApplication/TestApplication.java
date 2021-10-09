package ru.otus.spring.testApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.session.ConsoleSessionService;
import ru.otus.spring.test.test.TestService;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class TestApplication implements Application{

    private final ConsoleSessionService consoleSessionService;
    private final TestService test;

    private final ConsoleUtil consoleUtil;

    @Autowired
    public TestApplication(ConsoleSessionService consoleSessionService, TestService test, ConsoleUtil consoleUtil) {
        this.consoleSessionService = consoleSessionService;
        this.test = test;
        this.consoleUtil = consoleUtil;
    }

    @Override
    public void start() {
        consoleSessionService.createSession();
        test.test();
        consoleUtil.print(consoleSessionService.getUser().toString() + ", " + test.printTestResult());
    }
}
