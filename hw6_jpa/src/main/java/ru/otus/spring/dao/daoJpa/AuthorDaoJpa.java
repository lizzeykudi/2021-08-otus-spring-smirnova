package ru.otus.spring.dao.daoJpa;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author insert(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

}
