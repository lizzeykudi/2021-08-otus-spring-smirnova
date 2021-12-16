package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

//import org.springframework.test.annotation.Rollback;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;

    @Test
//    @Rollback
    void insert() {
        Author author = new Author("Walls Craig");
        BookGenre bookGenre = new BookGenre("Spring");

        Book expectedBook = bookRepository.save(new Book("Spring in Action", author, bookGenre));
        Book actualBook = bookRepository.findById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Author author = authorRepository.save(new Author("Bruce Eckel"));
        BookGenre bookGenre = bookGenreRepository.save(new BookGenre("Java"));

        Book expectedBook = bookRepository.save(new Book("Spring in Action", author, bookGenre));
        Book actualBook = bookRepository.findById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void update() {
        Author author = authorRepository.save(new Author("Walls Craig"));
        BookGenre bookGenre = bookGenreRepository.save(new BookGenre("Spring"));
        Book expectedBook = bookRepository.save(new Book("Spring in Action", author, bookGenre));
//        expectedBook.setId(1);

        bookRepository.save(expectedBook);
        Book actualBook = bookRepository.findById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void deleteById() {
        Author author = new Author("Walls Craig");
        BookGenre bookGenre = new BookGenre("Spring");

        Book expectedBook = bookRepository.save(new Book("Spring in Action", author, bookGenre));

        assertThatCode(() -> bookRepository.findById(expectedBook.getId()).get())
                .doesNotThrowAnyException();

        bookRepository.deleteById(expectedBook.getId());

        assertThatThrownBy(() -> bookRepository.findById(expectedBook.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}