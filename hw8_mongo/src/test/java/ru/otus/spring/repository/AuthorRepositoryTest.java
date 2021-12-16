package ru.otus.spring.repository;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        Author expectedAuthor = authorRepository.save(new Author("Walls Craig"));
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).get();

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getById() {
        Author expectedAuthor = authorRepository.save(new Author("Walls Craig"));

        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).get();

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}