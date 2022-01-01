package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookService.CommentService;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/readComment")
    public String readComments(@RequestParam("id") int id, Model model) {
//        List<Person> persons = repository.findAll();
        List<Comment> comments = commentService.findAll(id);
        if (comments==null) {comments = new ArrayList<>();
        }
//        List<Book> books = bookService.findAll();
//        model.addAttribute("persons", persons);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @GetMapping("/addComment")
    public String addComment(Model model) {
//        model.addAttribute("id", id);
//        Book book = bookService.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
//        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        Comment comment = new Comment();
//        Book book = new Book();
//        book.setAuthor(new Author());
//        book.setBookGenre(new BookGenre());
        model.addAttribute("comment", comment);
        return "editComment";
    }

    @PostMapping("/editComment")
    public String savePerson(
            Comment comment,
            Author author,
            String bookAuthorName,
            Model model
    ) {
        Comment savedComment = commentService.updateComment(comment).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + comment.getId()));
model.addAttribute(savedComment);
//        Book savedBook = bookService.update(book).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + book.getId()));
//        Person saved = repository.save(person);
//        model.addAttribute(savedBook);
        //Ошибка! Нужен редирект!
        return "redirect:/comments";
    }

    @GetMapping("/editComment")
    public String editComment(@RequestParam("id") int id, Model model) {
        Book book = bookService.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
//        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "editBook";
    }

    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") int id, Model model) {
        bookService.delete(id);
//        Book book = bookService.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
//        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
//        model.addAttribute("book", book);
        return "redirect:/";
    }
}
