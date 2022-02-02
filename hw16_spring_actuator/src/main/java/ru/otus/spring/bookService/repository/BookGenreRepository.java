package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.book.BookGenre;

@RepositoryRestResource()
public interface BookGenreRepository extends CrudRepository<BookGenre, Long> {

//    List<BookGenre> findAll();


}
