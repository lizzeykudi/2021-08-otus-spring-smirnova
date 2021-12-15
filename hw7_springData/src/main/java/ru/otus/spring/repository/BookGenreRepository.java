package ru.otus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.BookGenre;

public interface BookGenreRepository extends CrudRepository<BookGenre, Long> {

}
