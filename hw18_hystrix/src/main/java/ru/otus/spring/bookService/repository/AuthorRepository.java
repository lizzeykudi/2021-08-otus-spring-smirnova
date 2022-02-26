package ru.otus.spring.bookService.repository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.book.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @HystrixCommand()
    List<Author> findAll();


}
