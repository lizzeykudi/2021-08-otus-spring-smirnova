package ru.otus.spring.entity;

import java.util.Map;

public class User {
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Map<String, String> userData) {
        this.firstName = userData.get("firstName");
        this.lastName = userData.get("lastName");
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
