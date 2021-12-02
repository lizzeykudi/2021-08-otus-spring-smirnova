package ru.otus.spring.bookService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookGenreRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.NoSuchElementException;

@Service
public class DefaultBookService implements BookService {

    ApplicationContext context;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookGenreRepository bookGenreRepository;
    CommentRepository commentRepository;

    public DefaultBookService(ApplicationContext context, BookRepository bookRepository, AuthorRepository authorRepository, BookGenreRepository bookGenreRepository, CommentRepository commentRepository) {
        this.context = context;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        return bookRepository.save(new Book(bookTitle, author, bookGenre)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public String getById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String update(long id, String bookTitle, long bookAuthorId, long bookGenreNameId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        book.setTitle(bookTitle);
        book.setAuthor(authorRepository.findById(bookAuthorId).orElseThrow(() -> new NoSuchElementException("No such author with bookId " + bookAuthorId)));
        book.setBookGenre(bookGenreRepository.findById(bookGenreNameId).orElseThrow(() -> new NoSuchElementException("No such bookGenre with bookId " + bookGenreNameId)));
        bookRepository.save(book);
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String delete(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        bookRepository.deleteById(id);
        return book.toString();
    }

}
