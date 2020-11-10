package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Repository для работы с жанрами должно")
class GenreRepositoryTest {
    private static final int EXPECTED_GENRES_COUNT = 4;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("возвращать ожидаемый жанр по его id")
    void findById() {
        Genre expectedGenre = genreRepository.findAll().get(0);
        String expectedGenreId = expectedGenre.getId();
        Optional<Genre> actualGenre = genreRepository.findById(expectedGenreId);
        assertThat(actualGenre).isPresent().get()
                .hasFieldOrPropertyWithValue("id", expectedGenreId)
                .hasFieldOrPropertyWithValue("name", expectedGenre.getName());
    }

    @Test
    @DisplayName("возвращать все жанры")
    void findAll() {
        List<Genre> allGenres = genreRepository.findAll();
        assertThat(allGenres).hasSize(EXPECTED_GENRES_COUNT);
    }
}