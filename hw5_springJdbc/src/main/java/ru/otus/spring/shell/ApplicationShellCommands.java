package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

@ShellComponent
public class ApplicationShellCommands {

    ApplicationContext context;
    BookDao bookDao;
    AuthorDao authorDao;
    BookGenreDao bookGenreDao;

    public ApplicationShellCommands(ApplicationContext context, BookDao bookDao, AuthorDao authorDao, BookGenreDao bookGenreDao) {
        this.context = context;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.bookGenreDao = bookGenreDao;
    }

    @ShellMethod(value = "insert, format: insert bookTitle bookAuthor bookGenreName",key = "insert")
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = authorDao.insert(new Author(bookAuthor));
        BookGenre bookGenre = bookGenreDao.insert(new BookGenre(bookGenreName));
        return bookDao.insert(new Book(bookTitle, author, bookGenre)).toString();
    }

    @ShellMethod(value = "get by id, format: get by id id",key = "get by id")
    public String getById(int id) {
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "update, format: update id bookTitle bookAuthorId bookGenreNameId",key = "update")
    public String update(int id, String bookTitle, int bookAuthorId, int bookGenreNameId) {
        Book book = bookDao.getById(id);
        book.setTitle(bookTitle);
        book.setAuthor(authorDao.getById(bookAuthorId));
        book.setBookGenre(bookGenreDao.getById(bookGenreNameId));
        bookDao.update(book);
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "delete, format: delete id",key = "delete")
    public String delete(int id) {
        Book book = bookDao.getById(id);
        bookDao.deleteById(id);
        return book.toString();
    }
}