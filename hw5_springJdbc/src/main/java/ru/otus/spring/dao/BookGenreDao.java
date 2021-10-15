package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookGenre;

public interface BookGenreDao {

    BookGenre insert(BookGenre bookGenre);

    BookGenre getById(long id);
}
