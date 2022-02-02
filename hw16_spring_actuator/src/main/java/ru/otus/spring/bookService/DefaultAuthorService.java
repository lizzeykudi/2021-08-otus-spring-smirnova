package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookService.repository.AuthorRepository;

@Service
public class DefaultAuthorService implements AuthorService{

    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

//    @Override
//    public List<Author> findAll() {
//        return authorRepository.findAll();
//    }
}
