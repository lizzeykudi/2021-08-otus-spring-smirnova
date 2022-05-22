package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.spring.bookService.repository.AuthorRepository;
import ru.otus.spring.domain.Author;

@Service
public class DefaultAuthorService implements AuthorService{

    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }
}
