package ru.otus.spring.bookService;

import ru.otus.spring.domain.BookGenre;

import java.util.List;

public interface BookGenreService {

    List<BookGenre> findAll();

}
