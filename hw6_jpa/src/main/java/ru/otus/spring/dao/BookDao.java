package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface BookDao {

    Book insert(Book book);

    Optional<Book> getById(long id);

    void update(Book book);

    void deleteById(long id);
}
