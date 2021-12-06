package ru.otus.spring.dao.daoJpa;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.BookGenre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class BookGenreDaoJpa implements BookGenreDao {

    @Override
    public BookGenre insert(BookGenre bookGenre) {
        entityManager.persist(bookGenre);
        return bookGenre;
    }

    @PersistenceContext
    private final EntityManager entityManager;

    public BookGenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BookGenre> getById(long id) {
        return Optional.ofNullable(entityManager.find(BookGenre.class, id));
    }
}
