package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, BookGenreDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Autowired
    private BookGenreDaoJdbc bookGenreDaoJdbc;

    @Test
    void insert() {
        Author author = authorDaoJdbc.insert(new Author("Walls Craig"));
        BookGenre bookGenre = bookGenreDaoJdbc.insert(new BookGenre("Spring"));

        Book expectedBook = bookDaoJdbc.insert(new Book("Spring in Action", author, bookGenre));
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Author author = new Author("Bruce Eckel");
        author.setId(1);
        BookGenre bookGenre = new BookGenre("Java");
        bookGenre.setId(1);

        Book expectedBook = new Book("Thinking in java", author, bookGenre);
        expectedBook.setId(1);
        Book actualBook = bookDaoJdbc.getById(1);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void update() {
        Author author = authorDaoJdbc.insert(new Author("Walls Craig"));
        BookGenre bookGenre = bookGenreDaoJdbc.insert(new BookGenre("Spring"));
        Book expectedBook = new Book("Spring in Action", author, bookGenre);
        expectedBook.setId(1);

        bookDaoJdbc.update(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void deleteById() {
                assertThatCode(() -> bookDaoJdbc.getById(1))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(1);

        assertThatThrownBy(() -> bookDaoJdbc.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}