package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.bookService.CommentService;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.NoSuchElementException;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final BookService bookService;

    @Autowired
    public CommentController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @GetMapping("/readComment")
    public String readComments(@RequestParam("id") int id, Model model) {
        List<Comment> comments = commentService.findAll(id);
        if (comments == null) {
            comments = new ArrayList<>();
        }
        model.addAttribute("comments", comments);
        model.addAttribute("bookId", id);
        return "comments";
    }

    @GetMapping("/addComment")
    public String addComment(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("bookId", bookId);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("bookId", bookId);
        return "editComment";
    }

    @PostMapping("/editComment")
    public String saveComment(
            Comment comment,
            int bookId,
            Model model
    ) {

        Comment savedComment = commentService.updateComment(comment).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + comment.getId()));
        Book book = bookService.getById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        if (!book.getComments().contains(savedComment)) {
            book.addComment(comment);
            bookService.update(book);
            model.addAttribute(savedComment);
        }
        return "redirect:/readComment?id=" + bookId;
    }

    @GetMapping("/editComment")
    public String editComment(@RequestParam("id") int id, @RequestParam("bookId") int bookId, Model model) {
        Comment comment = commentService.getComment(id).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + id));
        model.addAttribute("comment", comment);
        model.addAttribute("bookId", bookId);
        return "editComment";
    }

    @GetMapping("/deleteComment")
    public String deletePage(@RequestParam("id") int id, int bookId, Model model) {
        commentService.deleteComment(bookId, id);
        return "redirect:/readComment?id=" + bookId;
    }
}
