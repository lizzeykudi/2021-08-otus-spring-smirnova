package ru.otus.spring.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.entity.User;
import ru.otus.spring.util.consoleUtil.ConsoleUtil;

@Component
public class SessionService {

    private User user;

    private final ConsoleUtil consoleUtil;

    public SessionService(ConsoleUtil consoleUtil) {
        this.consoleUtil = consoleUtil;
    }

    public void createSession() {
        user = new User(consoleUtil.signUp());
    }

    public User getUser() {
        return user;
    }
}
