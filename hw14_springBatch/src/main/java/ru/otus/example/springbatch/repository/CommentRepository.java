package ru.otus.example.springbatch.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.example.springbatch.domain.mongo.Comment;

public interface CommentRepository extends CrudRepository<Comment, String> {
}
