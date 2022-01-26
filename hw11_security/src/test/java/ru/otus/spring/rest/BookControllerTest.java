package ru.otus.spring.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.bookService.AuthorService;
import ru.otus.spring.bookService.BookGenreService;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.userService.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookGenreService bookGenreService;

    @MockBean
    private UserService userService;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )

    @Test
    void getBooks() {
    }

    @Test
    void getAuthors() {
    }

    @Test
    void getBookGenres() {
    }

    @Test
    void addBook() {
    }

    @Test
    void testAddBook() {
    }

    @Test
    void deleteBook() {
    }
}