package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.book.Author;

@RepositoryRestResource()
public interface AuthorRepository extends CrudRepository<Author, Long> {



}
