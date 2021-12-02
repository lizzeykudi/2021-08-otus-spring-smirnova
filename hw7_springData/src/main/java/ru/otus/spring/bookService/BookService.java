package ru.otus.spring.bookService;

public interface BookService {

    String insert(String bookTitle, String bookAuthor, String bookGenreName);

    String getById(long id);

    String update(long id, String bookTitle, long bookAuthorId, long bookGenreNameId);

    String delete(long id);
}
