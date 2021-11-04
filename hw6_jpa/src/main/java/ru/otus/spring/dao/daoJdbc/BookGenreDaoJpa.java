package ru.otus.spring.dao.daoJdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BookGenreDaoJpa implements BookGenreDao {

    @Override
    @Transactional
    public BookGenre insert(BookGenre bookGenre) {
        if (bookGenre.getId() == 0) {
            entityManager.persist(bookGenre);
            return bookGenre;
        } else {
            return entityManager.merge(bookGenre);
        }
    }

    @PersistenceContext
    private final EntityManager entityManager;

    public BookGenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Optional<BookGenre> getById(long id) {
        return Optional.ofNullable(entityManager.find(BookGenre.class, id));
    }
}
