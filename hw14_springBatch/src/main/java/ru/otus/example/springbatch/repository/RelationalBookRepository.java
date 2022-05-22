package ru.otus.example.springbatch.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.example.springbatch.domain.relational.Book;

public interface RelationalBookRepository extends CrudRepository<Book, Long> {

}
