package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();
}
