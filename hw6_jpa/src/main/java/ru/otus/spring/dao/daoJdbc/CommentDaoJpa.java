package ru.otus.spring.dao.daoJdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
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
