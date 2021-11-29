package ru.otus.spring.dao.daoJpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.BookGenre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BookGenreDaoJpa.class})
class BookGenreDaoJpaTest {

    @Autowired
    private BookGenreDaoJpa bookGenreDaoJpa;

    @Test
    void insert() {
        BookGenre expectedBookGenre = bookGenreDaoJpa.insert(new BookGenre("Spring"));
        BookGenre actualBookGenre =   bookGenreDaoJpa.getById(expectedBookGenre.getId()).get();

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }

    @Test
    void getById() {
        BookGenre expectedBookGenre = bookGenreDaoJpa.insert(new BookGenre("Spring"));


        BookGenre actualBookGenre = bookGenreDaoJpa.getById(expectedBookGenre.getId()).get();

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }
}