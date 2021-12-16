package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.NoSuchElementException;

@Service
public class DefaultCommentService implements CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public DefaultCommentService(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public String comment(String bookId, String text) {
        Comment comment = new Comment(text);
        commentRepository.save(comment);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.addComment(comment);
        bookRepository.save(book);
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).toString();
    }

    @Override
    @Transactional
    public String deleteComment(String bookId, String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + commentId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.removeComment(comment);
        bookRepository.save(book);
        commentRepository.deleteById(commentId);
        return comment.toString();
    }

    @Override
    @Transactional
    public String updateComment(String bookId, String commentId, String text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + commentId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.removeComment(comment);
        comment.setText(text);
        book.addComment(comment);
        commentRepository.save(comment);
        bookRepository.save(book);
        return commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId" + commentId)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public String getComment(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public String getCommentsByBookId(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).getComments().toString();
    }
}
