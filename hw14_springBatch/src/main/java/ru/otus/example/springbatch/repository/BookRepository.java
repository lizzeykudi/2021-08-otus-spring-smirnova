package ru.otus.example.springbatch.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.example.springbatch.domain.mongo.Book;

public interface BookRepository extends CrudRepository<Book, String> {

}
