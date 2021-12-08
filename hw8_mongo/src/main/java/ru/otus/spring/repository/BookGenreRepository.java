package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.BookGenre;

public interface BookGenreRepository extends MongoRepository<BookGenre, String> {

}
