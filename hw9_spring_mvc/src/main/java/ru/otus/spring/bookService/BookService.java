package ru.otus.spring.bookService;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    String insert(String bookTitle, String bookAuthor, String bookGenreName);

    Optional<Book> getById(long id);

    Optional<Book> update(Book book);

    String delete(long id);
}
