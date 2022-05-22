package ru.otus.spring.bookService;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Author;

public interface AuthorService {

    Flux<Author> findAll();
}
