package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.BookGenre;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookGenreDaoJdbc.class})
class BookGenreDaoJdbcTest {

    @Autowired
    private BookGenreDaoJdbc bookGenreDaoJdbc;

    @Test
    void insert() {
        BookGenre expectedBookGenre = bookGenreDaoJdbc.insert(new BookGenre("Spring"));
        BookGenre actualBookGenre =   bookGenreDaoJdbc.getById(expectedBookGenre.getId());

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }

    @Test
    void getById() {
        BookGenre expectedBookGenre = new BookGenre("Java");
        expectedBookGenre.setId(1);

        BookGenre actualBookGenre = bookGenreDaoJdbc.getById(1);

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }
}