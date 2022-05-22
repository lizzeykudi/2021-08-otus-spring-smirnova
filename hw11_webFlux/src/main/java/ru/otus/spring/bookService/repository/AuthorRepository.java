package ru.otus.spring.bookService.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findAll();
    Mono<Author> findById(String id);
}
