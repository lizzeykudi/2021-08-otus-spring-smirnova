package ru.otus.spring.bookService;

public interface BookService {

    String insert(String bookTitle, String bookAuthor, String bookGenreName);

    String getById(String id);

    String update(String id, String bookTitle, String bookAuthorId, String bookGenreNameId);

    String delete(String id);
}
