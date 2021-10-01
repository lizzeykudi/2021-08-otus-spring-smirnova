package ru.otus.spring.testApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.session.SessionService;
import ru.otus.spring.test.test.TestService;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class TestApplication {

    private final SessionService sessionService;
    private final TestService test;

    private final ConsoleUtil consoleUtil;

    @Autowired
    public TestApplication(SessionService sessionService, TestService test, ConsoleUtil consoleUtil) {
        this.sessionService = sessionService;
        this.test = test;
        this.consoleUtil = consoleUtil;
    }

    public void game() {
        sessionService.createSession();
        test.test();
        consoleUtil.print(sessionService.getUser().toString() + ", " + test.printTestResult());
    }
}
