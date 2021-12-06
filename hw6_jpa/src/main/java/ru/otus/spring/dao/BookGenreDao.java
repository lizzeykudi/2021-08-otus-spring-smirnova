package ru.otus.spring.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookGenre;

import java.util.Optional;

public interface BookGenreDao {

    BookGenre insert(BookGenre bookGenre);

    Optional<BookGenre> getById(long id);
}
