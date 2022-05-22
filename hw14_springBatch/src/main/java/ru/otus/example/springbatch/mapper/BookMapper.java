package ru.otus.example.springbatch.mapper;

import org.springframework.stereotype.Service;
import ru.otus.example.springbatch.domain.relational.Author;
import ru.otus.example.springbatch.domain.relational.Book;
import ru.otus.example.springbatch.domain.relational.BookGenre;

@Service
public class BookMapper {
    public Book map(ru.otus.example.springbatch.domain.mongo.Book book) {
        return new Book(book.getTitle(), map(book.getAuthor()), map(book.getBookGenre()));
    }

    private Author map(ru.otus.example.springbatch.domain.mongo.Author author) {
        return new Author(author.getName());
    }

    private BookGenre map(ru.otus.example.springbatch.domain.mongo.BookGenre bookGenre) {
        return new BookGenre(bookGenre.getTitle());
    }
}
