package ru.otus.spring.dao.daoJpa;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment insert(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void update(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(getById(id).get());
    }
}
