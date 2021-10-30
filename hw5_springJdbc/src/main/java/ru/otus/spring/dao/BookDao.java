package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

public interface BookDao {

    Book insert(Book book);

    Book getById(long id);

    void update(Book book);

    void deleteById(long id);
}
