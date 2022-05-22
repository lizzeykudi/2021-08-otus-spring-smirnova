package ru.otus.example.springbatch.bookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.springbatch.domain.mongo.Author;
import ru.otus.example.springbatch.domain.mongo.Book;
import ru.otus.example.springbatch.domain.mongo.BookGenre;
import ru.otus.example.springbatch.repository.AuthorRepository;
import ru.otus.example.springbatch.repository.BookGenreRepository;
import ru.otus.example.springbatch.repository.BookRepository;

import java.util.NoSuchElementException;

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
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        authorRepository.save(author);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        bookGenreRepository.save(bookGenre);
        return bookRepository.save(new Book(bookTitle, author, bookGenre)).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public String getById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String update(String id, String bookTitle, String bookAuthorId, String bookGenreNameId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        book.setTitle(bookTitle);
        book.setAuthor(authorRepository.findById(bookAuthorId).orElseThrow(() -> new NoSuchElementException("No such author with bookId " + bookAuthorId)));
        book.setBookGenre(bookGenreRepository.findById(bookGenreNameId).orElseThrow(() -> new NoSuchElementException("No such bookGenre with bookId " + bookGenreNameId)));
        bookRepository.save(book);
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String delete(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        bookRepository.deleteById(id);
        return book.toString();
    }

}
