package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;

    private Author author;

    private BookGenre bookGenre;

    public Book(String title, Author author, BookGenre bookGenre) {
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", bookGenre=" + bookGenre +
                '}';
    }
}
