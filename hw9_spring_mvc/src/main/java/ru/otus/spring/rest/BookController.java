package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
//        List<Person> persons = repository.findAll();
        List<Book> books = bookService.findAll();
//        model.addAttribute("persons", persons);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") int id, Model model) {
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

    @GetMapping("/add")
    public String addPage(Model model) {
//        Book book = bookService.getById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
//        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        Book book = new Book();
        book.setAuthor(new Author());
        book.setBookGenre(new BookGenre());
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String savePerson(
            Book book,
            Author author,
            String bookAuthorName,
            Model model
    ) {
        System.out.println(book);
        Book savedBook = bookService.update(book).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + book.getId()));
//        Person saved = repository.save(person);
        model.addAttribute(savedBook);
        //Ошибка! Нужен редирект!
        return "redirect:/";
    }
}
