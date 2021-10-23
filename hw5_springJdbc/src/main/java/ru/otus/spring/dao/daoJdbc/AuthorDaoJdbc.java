package ru.otus.spring.dao.daoJdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Author insert(Author author) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", author.getName());
        String INSERT_SQL = "INSERT INTO AUTHORS(name) values(:name)";
        namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
        author.setId(holder.getKey().intValue());
        return author;
    }

    @Override
    public Author getById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        String FETCH_SQL_BY_ID = "select id,name from authors where id = :id";
        return (Author) namedParameterJdbcTemplate.queryForObject(FETCH_SQL_BY_ID, parameters, new AuthorMapper());
    }

    class AuthorMapper implements RowMapper {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        }
    }
}
