package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({BookDaoJpa.class, AuthorDaoJpa.class, BookGenreDaoJpa.class})
class BookDaoJpaTest {

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Autowired
    private BookGenreDaoJpa bookGenreDaoJpa;

    @Test
    @Rollback
    void insert() {
        Author author = new Author("Walls Craig");
        BookGenre bookGenre = new BookGenre("Spring");

        Book expectedBook = bookDaoJpa.insert(new Book("Spring in Action", author, bookGenre));
        Book actualBook = bookDaoJpa.getById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Author author = authorDaoJpa.insert(new Author("Bruce Eckel"));
//        author.setId(1);
        BookGenre bookGenre = bookGenreDaoJpa.insert(new BookGenre("Java"));
//        bookGenre.setId(1);

        Book expectedBook = bookDaoJpa.insert(new Book("Spring in Action", author, bookGenre));
//        expectedBook.setId(1);
        Book actualBook = bookDaoJpa.getById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void update() {
        Author author = authorDaoJpa.insert(new Author("Walls Craig"));
        BookGenre bookGenre = bookGenreDaoJpa.insert(new BookGenre("Spring"));
        Book expectedBook = bookDaoJpa.insert(new Book("Spring in Action", author, bookGenre));
//        expectedBook.setId(1);

        bookDaoJpa.update(expectedBook);
        Book actualBook = bookDaoJpa.getById(expectedBook.getId()).get();

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void deleteById() {
        Author author = new Author("Walls Craig");
        BookGenre bookGenre = new BookGenre("Spring");

        Book expectedBook = bookDaoJpa.insert(new Book("Spring in Action", author, bookGenre));

        assertThatCode(() -> bookDaoJpa.getById(expectedBook.getId()).get())
                .doesNotThrowAnyException();

        bookDaoJpa.deleteById(expectedBook.getId());

        assertThatThrownBy(() -> bookDaoJpa.getById(expectedBook.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}