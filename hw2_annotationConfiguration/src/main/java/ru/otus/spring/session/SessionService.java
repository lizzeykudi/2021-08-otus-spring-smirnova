package ru.otus.spring.session;

import ru.otus.spring.entity.User;

public interface SessionService {

    public void createSession();

    public User getUser();
}
