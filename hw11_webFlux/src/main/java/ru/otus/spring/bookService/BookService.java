package ru.otus.spring.bookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.rest.dto.BookDto;

public interface BookService {

    Flux<Book> findAll();

    Mono<Book> insert(String bookTitle, String bookAuthor, String bookGenreName);

    Mono<Book> getById(String id);

    Mono<Book> update(BookDto book);

    Mono<Book> create(BookDto book);

    Mono<Void> delete(String id);
}
