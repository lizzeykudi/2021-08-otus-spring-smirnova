package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.BookGenre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookGenreRepositoryTest {

    @Autowired
    private BookGenreRepository bookGenreRepository;

    @Test
    void insert() {
        BookGenre expectedBookGenre = bookGenreRepository.save(new BookGenre("Spring"));
        BookGenre actualBookGenre =   bookGenreRepository.findById(expectedBookGenre.getId()).get();

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }

    @Test
    void getById() {
        BookGenre expectedBookGenre = bookGenreRepository.save(new BookGenre("Spring"));


        BookGenre actualBookGenre = bookGenreRepository.findById(expectedBookGenre.getId()).get();

        assertThat(actualBookGenre).usingRecursiveComparison().isEqualTo(expectedBookGenre);
    }
}