package ru.otus.spring.bookService;

public interface CommentService {

    String comment(long bookId, String text);

    String deleteComment(long bookId, long commentId);

    String updateComment(long id, String text);

    String getComment(long id);

    String getCommentsByBookId(long bookId);
}
