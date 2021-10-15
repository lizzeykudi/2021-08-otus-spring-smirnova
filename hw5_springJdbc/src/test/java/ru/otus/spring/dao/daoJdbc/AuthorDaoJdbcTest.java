package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({AuthorDaoJdbc.class})
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void insert() {
        Author expectedAuthor = authorDaoJdbc.insert(new Author("Walls Craig"));
        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getById() {
        Author expectedAuthor = new Author("Bruce Eckel");
        expectedAuthor.setId(1);

        Author actualAuthor = authorDaoJdbc.getById(1);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}