package ru.otus.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Сохранение нового автора и жанра вместе с книгой")
@DataMongoTest
@ComponentScan({"ru.otus.repository", "ru.otus.events"})
class MongoBookCascadeSaveEventsListenerTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAuthorAndGenreWithBook() {
        int authorsSize = mongoOperations.findAll(Author.class).size();
        int genreSize = mongoOperations.findAll(Genre.class).size();
        Book book = new Book(null, "newName", new Author(null, "newAuthor"), new Genre(null, "newGenre"));
        bookRepository.save(book);
        assertAll(() -> assertThat(mongoOperations.findAll(Author.class).size()).isEqualTo(authorsSize + 1),
                () -> assertThat(mongoOperations.findAll(Genre.class).size()).isEqualTo(genreSize + 1));
    }
}