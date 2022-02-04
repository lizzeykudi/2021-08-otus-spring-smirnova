package ru.otus.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.BookGenre;

@Configuration
public class MyRepositoryRestConfigurerAdapter implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Book.class, BookGenre.class, Author.class);
    }

}
