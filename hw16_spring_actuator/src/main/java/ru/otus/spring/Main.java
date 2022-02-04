package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.bookService.repository.AuthorRepository;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.BookGenre;
import ru.otus.spring.domain.user.Role;
import ru.otus.spring.domain.user.User;
import ru.otus.spring.userService.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private final UserService userService;
    private final BookGenreRepository bookGenreRepository;
    private final AuthorRepository authorRepository;

    public Main(UserService userService, BookGenreRepository bookGenreRepository, AuthorRepository authorRepository) {
        this.userService = userService;
        this.bookGenreRepository = bookGenreRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {

        bookGenreRepository.save(new BookGenre("Java"));
        bookGenreRepository.save(new BookGenre("Spring"));
        authorRepository.save(new Author("Bruce Eckel"));
        authorRepository.save(new Author("Craig Walls"));

        userService.insert(new User("login", "password", Arrays.asList(new Role("ADMIN"))));
    }
}
