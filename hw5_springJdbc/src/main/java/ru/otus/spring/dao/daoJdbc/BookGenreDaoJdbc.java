package ru.otus.spring.dao.daoJdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookGenreDaoJdbc implements BookGenreDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BookGenreDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public BookGenre insert(BookGenre bookGenre) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", bookGenre.getTitle());
        String INSERT_SQL = "INSERT INTO BOOK_GENRES(title) values(:title)";
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
        bookGenre.setId(holder.getKey().intValue());
        return bookGenre;
    }

    @Override
    public BookGenre getById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        String FETCH_SQL_BY_ID = "select * from book_genres where id = :id";
        return (BookGenre) namedParameterJdbcTemplate.queryForObject(FETCH_SQL_BY_ID, parameters, new BookMapper());
    }

    class BookMapper implements RowMapper {

        @Override
        public BookGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookGenre bookGenre = new BookGenre();
            bookGenre.setId(rs.getInt("id"));
            bookGenre.setTitle(rs.getString("title"));
            return bookGenre;
        }
    }
}
