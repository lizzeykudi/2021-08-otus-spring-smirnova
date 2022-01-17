package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();

}
