package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreDaoJpa.class)
@DisplayName("Dao для работы с жанрами должно")
class GenreDaoJpaTest {
    private static final long GENRE_ID = 3L;
    private static final String GENRE_NAME = "genreDetective";
    private static final int EXPECTED_GENRES_COUNT = 4;

    @Autowired
    private GenreDaoJpa genreDaoJpa;

    @Test
    @DisplayName("возвращать ожидаемый жанр по его id")
    void findById() {
        Optional<Genre> actualGenre = genreDaoJpa.findById(GENRE_ID);
        assertThat(actualGenre).isPresent().get()
                .hasFieldOrPropertyWithValue("id", GENRE_ID)
                .hasFieldOrPropertyWithValue("name", GENRE_NAME);
    }

    @Test
    @DisplayName("возвращать все жанры")
    void findAll() {
        List<Genre> allGenres = genreDaoJpa.findAll();
        assertThat(allGenres).hasSize(EXPECTED_GENRES_COUNT);
    }
}