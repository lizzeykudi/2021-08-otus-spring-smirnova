package ru.otus.spring.domain;

public class BookGenre {
    private long id;
    private String title;

    public BookGenre() {
    }

    public BookGenre(String title) {
        this.title = title;
    }

    public BookGenre(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
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
