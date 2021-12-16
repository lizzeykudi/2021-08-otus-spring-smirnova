package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Comment;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void insert() {

        Comment expectedComment = commentRepository.save(new Comment("nice"));
        Comment actualComment = commentRepository.findById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void getById() {

       Comment expectedComment = commentRepository.save(new Comment("nice"));

        Comment actualComment = commentRepository.findById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void update() {
        Comment expectedComment = commentRepository.save(new Comment("nice"));

        commentRepository.save(expectedComment);
        Comment actualComment = commentRepository.findById(expectedComment.getId()).get();

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void deleteById() {
Comment expectedComment = commentRepository.save(new Comment("nice"));

        assertThatCode(() -> commentRepository.findById(expectedComment.getId()).get())
                .doesNotThrowAnyException();

        commentRepository.deleteById(expectedComment.getId());

        assertThatThrownBy(() -> commentRepository.findById(expectedComment.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}