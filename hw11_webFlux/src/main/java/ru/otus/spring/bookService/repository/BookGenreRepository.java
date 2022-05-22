package ru.otus.spring.bookService.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.BookGenre;

public interface BookGenreRepository extends ReactiveMongoRepository<BookGenre, String> {

    Flux<BookGenre> findAll();
    Mono<BookGenre> findById(String id);


}
