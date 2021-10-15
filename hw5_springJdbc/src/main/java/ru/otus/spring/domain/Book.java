package ru.otus.spring.domain;

public class Book {
    private long id;
    private String title;
    private Author author;
    private BookGenre bookGenre;

    public Book() {
    }

    public Book(String title, Author author, BookGenre bookGenre) {
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
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
