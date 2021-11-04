package ru.otus.spring.dao.daoJdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Comment;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({CommentDaoJpa.class})
class CommentDaoJpaTest {

    @Autowired
    private CommentDaoJpa commentDaoJpa;

    @Test
    void insert() {

        Comment expectedComment = commentDaoJpa.insert(new Comment("nice"));
        Comment actualComment = commentDaoJpa.getById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void getById() {

       Comment expectedComment = commentDaoJpa.insert(new Comment("nice"));

        Comment actualComment = commentDaoJpa.getById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void update() {
        Comment expectedComment = commentDaoJpa.insert(new Comment("nice"));

        commentDaoJpa.update(expectedComment);
        Comment actualComment = commentDaoJpa.getById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void deleteById() {
Comment expectedComment = commentDaoJpa.insert(new Comment("nice"));

        assertThatCode(() -> commentDaoJpa.getById(expectedComment.getId()).get())
                .doesNotThrowAnyException();

        commentDaoJpa.deleteById(expectedComment.getId());

        assertThatThrownBy(() -> commentDaoJpa.getById(expectedComment.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}