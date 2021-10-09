package ru.otus.spring.test.questions.questions;

import java.util.InputMismatchException;

public class WrongAnswerNumber extends InputMismatchException {
    public WrongAnswerNumber() {
        super();
    }
}
