package ru.otus.spring.dao.daoJdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository

public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        if (book.getId() == 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    @Transactional
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    @Transactional
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        entityManager.remove(getById(id).get());
    }

}
