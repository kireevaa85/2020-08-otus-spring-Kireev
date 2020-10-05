package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Dao для работы с авторами должно")
class AuthorDaoJdbcTest {
    private static final long KING_ID = 2L;
    private static final String MISHA_NAME = "authorKing";
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    @DisplayName("возвращать ожидаемого автора по его id")
    void getById() {
        Author actualAuthor = authorDaoJdbc.getById(KING_ID);
        assertThat(actualAuthor)
                .hasFieldOrPropertyWithValue("id", KING_ID)
                .hasFieldOrPropertyWithValue("name", MISHA_NAME);
    }

    @Test
    @DisplayName("возвращать всех авторов")
    void getAll() {
        List<Author> allAuthors = authorDaoJdbc.getAll();
        assertThat(allAuthors).hasSize(EXPECTED_AUTHORS_COUNT);
    }

}