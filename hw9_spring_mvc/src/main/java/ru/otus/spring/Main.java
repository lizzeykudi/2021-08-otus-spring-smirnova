package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.bookService.BookService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private BookService  bookService;

    @PostConstruct
    public void init() {
        bookService.insert("12","13","14");
    }
}
