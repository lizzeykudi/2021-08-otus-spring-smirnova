package ru.otus.spring.test.questions.questions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    Question question;

    @BeforeEach
    void setUp() {
        question = new Question(new ArrayList<>(Arrays.asList(new String[]{"question", "answer1", "answer2", "answer1"})));
    }

    @Test
    void getAnswer() {
        assertEquals("answer2", question.getAnswer(2));
    }

    @Test
    void isCorrectAnswer() {
        assertEquals(false, question.isCorrectAnswer("answer2"));
    }
}