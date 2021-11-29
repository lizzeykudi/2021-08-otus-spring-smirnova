package ru.otus.spring.bookService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class DefaultBookService implements BookService {

    ApplicationContext context;
    BookDao bookDao;
    AuthorDao authorDao;
    BookGenreDao bookGenreDao;
    CommentDao commentDao;

    public DefaultBookService(ApplicationContext context, BookDao bookDao, AuthorDao authorDao, BookGenreDao bookGenreDao, CommentDao commentDao) {
        this.context = context;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.bookGenreDao = bookGenreDao;
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        return bookDao.insert(new Book(bookTitle, author, bookGenre)).toString();
    }

    @Override
    @Transactional
    public String getById(int id) {
        return bookDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String update(int id, String bookTitle, int bookAuthorId, int bookGenreNameId) {
        Book book = bookDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        book.setTitle(bookTitle);
        book.setAuthor(authorDao.getById(bookAuthorId).orElseThrow(() -> new NoSuchElementException("No such author with bookId " + bookAuthorId)));
        book.setBookGenre(bookGenreDao.getById(bookGenreNameId).orElseThrow(() -> new NoSuchElementException("No such bookGenre with bookId " + bookGenreNameId)));
        bookDao.update(book);
        return bookDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id)).toString();
    }

    @Override
    @Transactional
    public String delete(int id) {
        Book book = bookDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        bookDao.deleteById(id);
        return book.toString();
    }

}
