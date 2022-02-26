package ru.otus.spring.bookService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookService.repository.AuthorRepository;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.bookService.repository.BookRepository;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.BookGenre;
import ru.otus.spring.rest.NotFoundException;
import ru.otus.spring.rest.dto.BookDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {
    int i = 0;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookGenreRepository bookGenreRepository;

    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository, BookGenreRepository bookGenreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookGenreRepository = bookGenreRepository;
    }

    @Override
    @Transactional
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000"),
            @HystrixProperty(name="execution.isolation.thread.interruptOnTimeout", value="true")
            })
    public List<Book> findAll() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if (i!=0) {
//            try {
//                Thread.sleep(50000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        } else {
//            i = 1;
//        return bookRepository.findAll(); }
        return bookRepository.findAll();
    }

    @Override
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        return bookRepository.save(new Book(bookTitle, author, bookGenre)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book update(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(NotFoundException::new);
        BookGenre bookGenre = bookGenreRepository.findById(bookDto.getBookGenreId()).orElseThrow(NotFoundException::new);
        Book book = bookRepository.findById(bookDto.getId()).orElse(new Book());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        book.setBookGenre(bookGenre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    @HystrixCommand(commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="7000"),
            @HystrixProperty(name="execution.isolation.thread.interruptOnTimeout", value="true")
    })

    public String delete(long id) {
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        bookRepository.deleteById(id);
        return book.toString();
    }

    public List<Book> fallbackMethod() {
        System.out.println("fallbackmethod");
        throw new RuntimeException();
//        return null;
    }
//
//    public Object fallbackMethod(Object object) {
//        System.out.println("fallbackmethof");
//        throw new RuntimeException();
//    }
//
//    public Object fallbackMethod(Object[] objects) {
//        System.out.println("fallbackmethof");
//        throw new RuntimeException();
//    }

}
