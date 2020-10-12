package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Repository для работы с авторами должно")
class AuthorRepositoryTest {
    private static final long KING_ID = 2L;
    private static final String MISHA_NAME = "authorKing";
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("возвращать ожидаемого автора по его id")
    void findById() {
        Optional<Author> actualAuthor = authorRepository.findById(KING_ID);
        assertThat(actualAuthor).isPresent().get()
                .hasFieldOrPropertyWithValue("id", KING_ID)
                .hasFieldOrPropertyWithValue("name", MISHA_NAME);
    }

    @Test
    @DisplayName("возвращать всех авторов")
    void findAll() {
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors).hasSize(EXPECTED_AUTHORS_COUNT);
    }
}