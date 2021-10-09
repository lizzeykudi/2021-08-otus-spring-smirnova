package ru.otus.spring.session;

import org.springframework.stereotype.Component;
import ru.otus.spring.entity.User;
import ru.otus.spring.util.outputUtil.OutputService;

@Component
public class ConsoleSessionService implements SessionService{

    private User user;

    private final OutputService outputService;

    public ConsoleSessionService(OutputService outputService) {
        this.outputService = outputService;
    }

    @Override
    public void createSession() {
        user = new User(outputService.signUp());
    }

    @Override
    public User getUser() {
        return user;
    }
}
