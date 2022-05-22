package ru.otus.example.springbatch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.example.springbatch.domain.mongo.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
