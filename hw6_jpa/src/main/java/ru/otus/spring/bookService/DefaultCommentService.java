package ru.otus.spring.bookService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class DefaultCommentService implements CommentService {
    ApplicationContext context;
    BookDao bookDao;
    AuthorDao authorDao;
    BookGenreDao bookGenreDao;
    CommentDao commentDao;

    public DefaultCommentService(ApplicationContext context, BookDao bookDao, AuthorDao authorDao, BookGenreDao bookGenreDao, CommentDao commentDao) {
        this.context = context;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.bookGenreDao = bookGenreDao;
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public String comment(int bookId, String text) {
        Comment comment = new Comment(text);
        Book book = bookDao.getById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.addComment(comment);
        bookDao.update(book);
        return bookDao.getById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).toString();
    }

    @Override
    @Transactional
    public String deleteComment(int bookId, int commentId) {
        Comment comment = commentDao.getById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + commentId));
        Book book = bookDao.getById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.removeComment(comment);
        bookDao.update(book);
        commentDao.deleteById(commentId);
        return comment.toString();
    }

    @Override
    @Transactional
    public String updateComment(int id, String text) {
        Comment comment = commentDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id));
        comment.setText(text);
        commentDao.update(comment);
        return commentDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId" + id)).toString();
    }

    @Override
    @Transactional
    public String getComment(int id) {
        return commentDao.getById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id)).toString();
    }


    @Override
    @Transactional
    public String getCommentsByBookId(int bookId) {
        return bookDao.getById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).getComments().toString();
    }
}
