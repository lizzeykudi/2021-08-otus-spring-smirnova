package ru.otus.spring.bookService;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
}
