package ru.otus.spring.testApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.session.ConsoleSessionService;
import ru.otus.spring.test.test.TestService;
import ru.otus.spring.util.outputUtil.OutputService;

@Component
public class TestApplication implements Application{

    private final ConsoleSessionService consoleSessionService;
    private final TestService test;

    private final OutputService outputService;

    @Autowired
    public TestApplication(ConsoleSessionService consoleSessionService, TestService test, OutputService outputService) {
        this.consoleSessionService = consoleSessionService;
        this.test = test;
        this.outputService = outputService;
    }

    @Override
    public void start() {
        consoleSessionService.createSession();
        test.test();
        outputService.print(consoleSessionService.getUser().toString() + ", " + test.printTestResult());
    }
}
