package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.Book;

import javax.annotation.PostConstruct;

@EnableWebFlux
@EnableMongoRepositories
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private final BookService bookService;

    @Autowired
    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {
        Flux.concat(
            bookService.insert("Thinking in Java", "Bruce Eckel", "Java"),
            bookService.insert("Spring in Action", "Craig Walls", "Spring")
        )
                .map(Book::toString)
                .subscribe(System.out::println);
    }
}
