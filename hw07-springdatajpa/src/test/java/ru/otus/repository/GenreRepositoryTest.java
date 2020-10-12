package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Repository для работы с жанрами должно")
class GenreRepositoryTest {
    private static final long GENRE_ID = 3L;
    private static final String GENRE_NAME = "genreDetective";
    private static final int EXPECTED_GENRES_COUNT = 4;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("возвращать ожидаемый жанр по его id")
    void findById() {
        Optional<Genre> actualGenre = genreRepository.findById(GENRE_ID);
        assertThat(actualGenre).isPresent().get()
                .hasFieldOrPropertyWithValue("id", GENRE_ID)
                .hasFieldOrPropertyWithValue("name", GENRE_NAME);
    }

    @Test
    @DisplayName("возвращать все жанры")
    void findAll() {
        List<Genre> allGenres = genreRepository.findAll();
        assertThat(allGenres).hasSize(EXPECTED_GENRES_COUNT);
    }
}