package ru.otus.spring.bookService.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAll();
    Mono<Book> findById(String id);
    Mono<Book> save(Mono<Book> book);
}
