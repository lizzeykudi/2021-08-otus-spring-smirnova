package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.book.Book;

@RepositoryRestResource()
public interface BookRepository extends CrudRepository<Book, Long> {
}
