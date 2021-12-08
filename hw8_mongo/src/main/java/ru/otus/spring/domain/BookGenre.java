package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book_genres")
public class BookGenre {
    @Id
    private String id;
    private String title;

    public BookGenre(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
