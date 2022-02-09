package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.bookService.BookService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private final BookService bookService;

    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {

        bookService.insert("Thinking in Java", "Bruce Eckel", "Java");
        bookService.insert("Spring in Action", "Craig Walls", "Spring");

    }
}
