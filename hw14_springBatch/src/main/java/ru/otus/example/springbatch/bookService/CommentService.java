package ru.otus.example.springbatch.bookService;

public interface CommentService {

    String comment(String bookId, String text);

    String deleteComment(String bookId, String commentId);

    String updateComment(String bookId, String id, String text);

    String getComment(String id);

    String getCommentsByBookId(String bookId);
}
