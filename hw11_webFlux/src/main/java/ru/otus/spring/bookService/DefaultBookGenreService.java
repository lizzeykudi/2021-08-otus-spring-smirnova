package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.domain.BookGenre;

@Service
public class DefaultBookGenreService implements BookGenreService{

    private final BookGenreRepository bookGenreRepository;

    public DefaultBookGenreService(BookGenreRepository bookGenreRepository) {
        this.bookGenreRepository = bookGenreRepository;
    }

    @Override
    public Flux<BookGenre> findAll() {
        return bookGenreRepository.findAll();
    }
}
