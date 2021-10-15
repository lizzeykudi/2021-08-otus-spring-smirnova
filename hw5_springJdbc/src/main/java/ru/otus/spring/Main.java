package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
//
        SpringApplication.run(Main.class);
//
//        BookDao bookDao = context.getBean(BookDao.class);
//        AuthorDao authorDao = context.getBean(AuthorDao.class);
//        BookGenreDao bookGenreDao = context.getBean(BookGenreDao.class);
//        Author author = authorDao.insert(new Author("Bruce Eckel"));
//        BookGenre bookGenre = bookGenreDao.insert(new BookGenre("Java"));
//        Book book = bookDao.insert(new Book("Thinking in java", author, bookGenre));
//        book.setTitle("lol");
//        bookDao.update(book);
//        bookDao.deleteById(2);
//        System.out.println(bookDao.getById(1).getTitle());
    }
}
