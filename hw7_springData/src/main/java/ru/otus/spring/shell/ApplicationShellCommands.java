package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.bookService.CommentService;

@ShellComponent
public class ApplicationShellCommands {

    private final BookService bookService;
    private final CommentService commentService;

    public ApplicationShellCommands(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "insert, format: insert bookTitle bookAuthor bookGenreName", key = "insert")
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        return bookService.insert(bookTitle, bookAuthor, bookGenreName);
    }

    @ShellMethod(value = "get by id, format: get by id id", key = "get by id")
    public String getById(int id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "update, format: update id bookTitle bookAuthorId bookGenreNameId", key = "update")
    public String update(int id, String bookTitle, int bookAuthorId, int bookGenreNameId) {
        return bookService.update(id, bookTitle, bookAuthorId, bookGenreNameId);
    }

    @ShellMethod(value = "delete, format: delete id", key = "delete")
    public String delete(int id) {
        return bookService.delete(id);
    }

    @ShellMethod(value = "comment , format: comment bookId text", key = "comment")
    public String comment(int bookId, String text) {
        return commentService.comment(bookId, text);
    }

    @ShellMethod(value = "delete comment , format:delete comment bookId commentId", key = "delete comment")
    public String deleteComment(int bookId, int commentId) {
        return commentService.deleteComment(bookId, commentId);
    }

    @ShellMethod(value = "update comment, format: update comment id text", key = "update comment")
    public String updateComment(int id, String text) {
        return commentService.updateComment(id, text);
    }

    @ShellMethod(value = "get comment, format: get comment id", key = "get comment")
    public String getComment(int id) {
        return commentService.getComment(id);
    }

    @ShellMethod(value = "get comments of book, format: get comments of book bookId", key = "get comments of book")
    public String getCommentsByBookId(int bookId) {
        return commentService.getCommentsByBookId(bookId);
    }
}