package ru.otus.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DisplayName("Удаление жанра при наличии/отсутствии с ним книг")
@DataMongoTest
@ComponentScan({"ru.otus.repository", "ru.otus.events"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class MongoGenreDeleteEventsListenerTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void shouldThrowExceptionByDeleteGenreWithBooksWithHim() {
        Genre genre = mongoOperations.findOne(query(where("name").is("Novel")), Genre.class);
        assertThrows(RuntimeException.class, () -> genreRepository.delete(genre));
    }

    @Test
    void shouldDeleteGenreWithoutBooksWithHim() {
        Genre genre = genreRepository.save(new Genre(null, "newGenre"));
        genreRepository.delete(genre);
        assertThat(mongoOperations.findOne(query(where("id").is(genre.getId())), Author.class)).isNull();
    }
}