package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.bookService.repository.PersonRepository;
import ru.otus.spring.domain.Person;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @Autowired
    private BookService  bookService;

    @PostConstruct
    public void init() {
        bookService.insert("12","13","14");

        repository.save(new Person("Pushkin"));
        repository.save(new Person("Lermontov"));
    }
}
