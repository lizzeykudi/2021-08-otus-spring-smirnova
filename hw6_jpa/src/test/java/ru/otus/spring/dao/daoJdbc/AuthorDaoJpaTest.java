package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorDaoJpa.class})
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Test
    void insert() {
        Author expectedAuthor = authorDaoJpa.insert(new Author("Walls Craig"));
        Author actualAuthor = authorDaoJpa.getById(expectedAuthor.getId()).get();

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getById() {
        Author expectedAuthor = authorDaoJpa.insert(new Author("Walls Craig"));

        Author actualAuthor = authorDaoJpa.getById(expectedAuthor.getId()).get();

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}