package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.domain.Comment;

import java.util.Optional;

@ShellComponent
public class ApplicationShellCommands {

    ApplicationContext context;
    BookDao bookDao;
    AuthorDao authorDao;
    BookGenreDao bookGenreDao;
    CommentDao commentDao;

    public ApplicationShellCommands(ApplicationContext context, BookDao bookDao, AuthorDao authorDao, BookGenreDao bookGenreDao, CommentDao commentDao) {
        this.context = context;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.bookGenreDao = bookGenreDao;
        this.commentDao = commentDao;
    }

    @ShellMethod(value = "insert, format: insert bookTitle bookAuthor bookGenreName",key = "insert")
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        return bookDao.insert(new Book(bookTitle, author, bookGenre)).toString();
    }

    @ShellMethod(value = "get by id, format: get by id id",key = "get by id")
    public String getById(int id) {
        return bookDao.getById(id).get().toString();
    }

    @ShellMethod(value = "update, format: update id bookTitle bookAuthorId bookGenreNameId",key = "update")
    public String update(int id, String bookTitle, int bookAuthorId, int bookGenreNameId) {
        Book book = bookDao.getById(id).get();
        book.setTitle(bookTitle);
        book.setAuthor(authorDao.getById(bookAuthorId).get());
        book.setBookGenre(bookGenreDao.getById(bookGenreNameId).get());
        bookDao.update(book);
        return bookDao.getById(id).get().toString();
    }

    @ShellMethod(value = "delete, format: delete id",key = "delete")
    public String delete(int id) {
        Book book = bookDao.getById(id).get();
        bookDao.deleteById(id);
        return book.toString();
    }

    @ShellMethod(value = "comment , format: comment bookId text",key = "comment")
    public String comment(int bookId, String text) {
        Comment comment = new Comment(text);
        Book book = bookDao.getById(bookId).get();
        book.addComment(comment);
        bookDao.update(book);
        return bookDao.getById(bookId).get().toString();
    }

    @ShellMethod(value = "delete comment , format:delete comment bookId commentId",key = "delete comment")
    public String deleteComment(int bookId, int commentId) {
        Comment comment = commentDao.getById(commentId).get();
        Book book = bookDao.getById(bookId).get();
        book.removeComment(comment);
        bookDao.update(book);
        commentDao.deleteById(commentId);
        return comment.toString();
    }

    @ShellMethod(value = "update comment, format: update comment id text",key = "update comment")
    public String updateComment(int id, String text) {
        Comment comment = commentDao.getById(id).get();
        comment.setText(text);
        commentDao.update(comment);
        return commentDao.getById(id).get().toString();
    }

    @ShellMethod(value = "get comment, format: get comment id",key = "get comment")
    public String getComment(int id) {
        return commentDao.getById(id).toString();
    }
}