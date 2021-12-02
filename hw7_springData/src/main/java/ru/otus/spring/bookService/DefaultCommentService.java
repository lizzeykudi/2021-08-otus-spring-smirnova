package ru.otus.spring.bookService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookGenreRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.NoSuchElementException;

@Service
public class DefaultCommentService implements CommentService {
    ApplicationContext context;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookGenreRepository bookGenreRepository;
    CommentRepository commentRepository;

    public DefaultCommentService(ApplicationContext context, BookRepository bookRepository, AuthorRepository authorRepository, BookGenreRepository bookGenreRepository, CommentRepository commentRepository) {
        this.context = context;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public String comment(long bookId, String text) {
        Comment comment = new Comment(text);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.addComment(comment);
        bookRepository.save(book);
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).toString();
    }

    @Override
    @Transactional
    public String deleteComment(long bookId, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + commentId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.removeComment(comment);
        bookRepository.save(book);
        commentRepository.deleteById(commentId);
        return comment.toString();
    }

    @Override
    @Transactional
    public String updateComment(long id, String text) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id));
        comment.setText(text);
        commentRepository.save(comment);
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId" + id)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public String getComment(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id)).toString();
    }


    @Override
    @Transactional(readOnly = true)
    public String getCommentsByBookId(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).getComments().toString();
    }
}
