package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookService.repository.AuthorRepository;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.bookService.repository.BookRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.rest.dto.BookDto;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookGenreRepository bookGenreRepository;

    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository, BookGenreRepository bookGenreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookGenreRepository = bookGenreRepository;
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        Mono<Author> savedAuthor = authorRepository.save(author);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        Mono<BookGenre> savedBookGenre = bookGenreRepository.save(bookGenre);
        return Mono.zip(savedAuthor, savedBookGenre)
                .flatMap(data -> bookRepository.save(new Book(bookTitle, data.getT1(), data.getT2())));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Mono<Book> update(BookDto bookDto) {
        Mono<Author> author = authorRepository.findById(bookDto.getAuthorId());
        Mono<BookGenre> bookGenre = bookGenreRepository.findById(bookDto.getBookGenreId());
        Mono<Book> book = bookRepository.findById(bookDto.getId());
        Mono<Book> toSave = Mono.zip(book, author, bookGenre)
                .map(data -> {
                    Book updatedBook = data.getT1();
                    updatedBook.setTitle(bookDto.getTitle());
                    updatedBook.setAuthor(data.getT2());
                    updatedBook.setBookGenre(data.getT3());
                    return updatedBook;
                });

        return bookRepository.save(toSave);
    }

    @Override
    @Transactional
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

}
