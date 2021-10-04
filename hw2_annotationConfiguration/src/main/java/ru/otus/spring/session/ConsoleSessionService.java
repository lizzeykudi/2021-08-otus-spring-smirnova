package ru.otus.spring.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.entity.User;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class ConsoleSessionService implements SessionService{

    private User user;

    private final ConsoleUtil consoleUtil;

    public ConsoleSessionService(ConsoleUtil consoleUtil) {
        this.consoleUtil = consoleUtil;
    }

    @Override
    public void createSession() {
        user = new User(consoleUtil.signUp());
    }

    @Override
    public User getUser() {
        return user;
    }
}
