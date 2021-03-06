package ru.otus.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DisplayName("Удаление автора при наличии/отсутствии с ним книг")
@DataMongoTest
@ComponentScan({"ru.otus.repository", "ru.otus.events"})
class MongoAuthorDeleteEventsListenerTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldThrowExceptionByDeleteAuthorWithBooksWithHim() {
        Author author = mongoOperations.findOne(query(where("name").is("Tolstoy")), Author.class);
        assertThrows(RuntimeException.class, () -> authorRepository.delete(author));
    }

    @Test
    void shouldDeleteAuthorWithoutBooksWithHim() {
        Author author = mongoOperations.save(new Author(null, "newAuthor"));
        authorRepository.delete(author);
        assertThat(mongoOperations.findOne(query(where("id").is(author.getId())), Author.class)).isNull();
    }
}