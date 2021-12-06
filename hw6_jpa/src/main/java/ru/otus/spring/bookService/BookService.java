package ru.otus.spring.bookService;

public interface BookService {

    String insert(String bookTitle, String bookAuthor, String bookGenreName);

    String getById(int id);

    String update(int id, String bookTitle, int bookAuthorId, int bookGenreNameId);

    String delete(int id);
}
