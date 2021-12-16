package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;

//    @ManyToOne(targetEntity = Author.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
//    @JoinColumn(name = "author_id")
    private Author author;

//    @ManyToOne(targetEntity = BookGenre.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
//    @JoinColumn(name = "book_genre_id")
    private BookGenre bookGenre;

//    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<Comment> comments = new ArrayList<>();

    public Book(String title, Author author, BookGenre bookGenre) {
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
    }

    public Book(String title, Author author, BookGenre bookGenre, List<Comment> comments) {
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", bookGenre=" + bookGenre +
                ", comments=" + comments +
                '}';
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
}
