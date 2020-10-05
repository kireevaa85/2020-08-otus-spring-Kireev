package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorDaoJpa.class)
@DisplayName("Dao для работы с авторами должно")
class AuthorDaoJpaTest {
    private static final long KING_ID = 2L;
    private static final String MISHA_NAME = "authorKing";
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Test
    @DisplayName("возвращать ожидаемого автора по его id")
    void findById() {
        Optional<Author> actualAuthor = authorDaoJpa.findById(KING_ID);
        assertThat(actualAuthor).isPresent().get()
                .hasFieldOrPropertyWithValue("id", KING_ID)
                .hasFieldOrPropertyWithValue("name", MISHA_NAME);
    }

    @Test
    @DisplayName("возвращать всех авторов")
    void findAll() {
        List<Author> allAuthors = authorDaoJpa.findAll();
        assertThat(allAuthors).hasSize(EXPECTED_AUTHORS_COUNT);
    }

}