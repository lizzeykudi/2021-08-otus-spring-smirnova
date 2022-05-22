package ru.otus.spring.bookService;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

public interface BookGenreService {

    Flux<BookGenre> findAll();

}
