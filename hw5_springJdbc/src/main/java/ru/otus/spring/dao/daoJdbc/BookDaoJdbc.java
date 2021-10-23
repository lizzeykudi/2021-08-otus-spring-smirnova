package ru.otus.spring.dao.daoJdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final AuthorDao authorDao;
    private final BookGenreDao bookGenreDao;

    public BookDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate, AuthorDao authorDao, BookGenreDao bookGenreDao) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorDao = authorDao;
        this.bookGenreDao = bookGenreDao;
    }

    @Override
    public Book insert(Book book) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("book_genre_id", book.getBookGenre().getId());
        String INSERT_SQL = "INSERT INTO BOOKS(title, author_id, book_genre_id)  values(:title,:author_id,:book_genre_id)";
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
        book.setId(holder.getKey().intValue());
        return book;
    }

    @Override
    public Book getById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        String FETCH_SQL_BY_ID =
                "select books.id," +
                        "books.title," +
                        "authors.id as author_id," +
                        "authors.name as author_name," +
                        "book_genres.id as book_genre_id," +
                        "book_genres.title as book_genre_title" +
                        " from books join authors on books.AUTHOR_ID=authors.id join book_genres on book_genres.id=books.book_genre_id where books.id = :id";
        try {
            return (Book) namedParameterJdbcTemplate.queryForObject(FETCH_SQL_BY_ID, parameters, new BookMapper());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println("No book id " + id);
            throw emptyResultDataAccessException;
        }
    }

    @Override
    public void update(Book book) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("book_genre_id", book.getBookGenre().getId());
        String UPDATE_SQL = "UPDATE books SET title = :title, author_id= :author_id, book_genre_id=:book_genre_id WHERE id = :id";
        namedParameterJdbcTemplate.update(UPDATE_SQL, parameters, holder);
    }

    @Override
    public void deleteById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        String DELETE_SQL = "DELETE from books WHERE id = :id";
        namedParameterJdbcTemplate.update(DELETE_SQL, parameters);
    }

    class BookMapper implements RowMapper {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(new Author(rs.getInt("author_id"), rs.getString("author_name")));
            book.setBookGenre(new BookGenre(rs.getInt("book_genre_id"), rs.getString("book_genre_title")));
            return book;
        }

    }
}
