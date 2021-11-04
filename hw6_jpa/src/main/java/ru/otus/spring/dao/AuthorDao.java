package ru.otus.spring.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author);

    Optional<Author> getById(long id);
}
