package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Repository для работы с авторами должно")
class AuthorRepositoryTest {
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("возвращать ожидаемого автора по его id")
    void findById() {
        Author expectedAuthor = authorRepository.findAll().get(0);
        String expectedAuthorId = expectedAuthor.getId();
        Optional<Author> actualAuthor = authorRepository.findById(expectedAuthorId);
        assertThat(actualAuthor).isPresent().get()
                .hasFieldOrPropertyWithValue("id", expectedAuthorId)
                .hasFieldOrPropertyWithValue("name", expectedAuthor.getName());
    }

    @Test
    @DisplayName("возвращать всех авторов")
    void findAll() {
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors).hasSize(EXPECTED_AUTHORS_COUNT);
    }
}