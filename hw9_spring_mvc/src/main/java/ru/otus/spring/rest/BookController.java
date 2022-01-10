package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookService.AuthorService;
import ru.otus.spring.bookService.BookGenreService;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookGenreService bookGenreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, BookGenreService bookGenreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookGenreService = bookGenreService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") int id, Model model) {
        Book book = bookService.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        model.addAttribute("book", book);

        model.addAttribute("bookGenres", bookGenreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "editBook";
    }

    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") int id, Model model) {
        bookService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        Book book = new Book();
        book.setAuthor(new Author());
        book.setBookGenre(new BookGenre());
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String saveBook(
            Book book,
            Model model
    ) {
        Book savedBook = bookService.update(book).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + book.getId()));
        model.addAttribute(savedBook);
        return "redirect:/";
    }
}
