package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.book.Comment;

@RepositoryRestResource()
public interface CommentRepository extends CrudRepository<Comment, Long> {
//    List<Comment> findAll();

}
