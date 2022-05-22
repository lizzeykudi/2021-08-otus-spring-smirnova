package ru.otus.example.springbatch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.springbatch.domain.mongo.BookGenre;

public interface BookGenreRepository extends MongoRepository<BookGenre, String> {

}
