package ru.otus.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookService.AuthorService;
import ru.otus.spring.bookService.BookGenreService;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.rest.dto.BookDto;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(Book.class);


    private final BookService bookService;
    private final AuthorService authorService;
    private final BookGenreService bookGenreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, BookGenreService bookGenreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookGenreService = bookGenreService;
    }

    @GetMapping("/api/books")
    public Flux<Book> listPage() {
        return bookService.findAll();
    }

    @GetMapping("/api/authors")
    public Flux<Author> getAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/api/bookGenres")
    public Flux<BookGenre> getBookGenres() {
        return bookGenreService.findAll();
    }

    @PostMapping("/api/books")
    public Mono<Book> addBook(@RequestBody BookDto bookDto) {
        log.info("Request to create book: {}", bookDto);
        return bookService.create(bookDto);
    }

    @PutMapping("/api/books/{id}")
    public Mono<Book> addBook(@RequestBody BookDto bookDto, @PathVariable String id) {
        bookDto.setId(id);
        log.info("Request to edit book: {}", bookDto);
        return bookService.update(bookDto);
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        log.info("Request to delete bookId: {}", id);
        return bookService.delete(id);
    }

}
