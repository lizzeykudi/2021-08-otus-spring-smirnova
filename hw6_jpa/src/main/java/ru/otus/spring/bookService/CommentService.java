package ru.otus.spring.bookService;

public interface CommentService {

    String comment(int bookId, String text);

    String deleteComment(int bookId, int commentId);

    String updateComment(int id, String text);

    String getComment(int id);

    String getCommentsByBookId(int bookId);
}
