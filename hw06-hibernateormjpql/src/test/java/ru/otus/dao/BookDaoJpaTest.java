package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(BookDaoJpa.class)
@DisplayName("Dao для работы с книгами должно")
class BookDaoJpaTest {
    private static final int EXPECTED_BOOKS_COUNT = 5;
    private static final long LONG_WALK_ID = 2L;
    private static final String LONG_WALK_NAME = "Long walk";
    private static final long EXPECTED_NEW_ID = 6;
    private static final long PUSHKIN_ID = 3L;
    private static final long KING_ID = 2L;
    private static final long HORROR_ID = 4L;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Test
    @DisplayName("возвращать ожидаемое количество книг в БД")
    void count() {
        long count = bookDaoJpa.count();
        assertThat(count).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @DisplayName("добавлять книгу в БД")
    void insert() {
        Book expectedBook = new Book(null,
                "Spring in Action",
                em.find(Author.class, 2L),
                em.find(Genre.class, 4L));
        Book savedBook = bookDaoJpa.save(expectedBook);
        assertThat(savedBook.getId()).isEqualTo(EXPECTED_NEW_ID);
        Optional<Book> actualBook = bookDaoJpa.findById(EXPECTED_NEW_ID);
        assertThat(actualBook).isPresent().get()
                .isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу по ее id")
    void getById() {
        Optional<Book> actualBook = bookDaoJpa.findById(LONG_WALK_ID);
        assertThat(actualBook).isPresent().get()
                .hasFieldOrPropertyWithValue("id", LONG_WALK_ID)
                .hasFieldOrPropertyWithValue("name", LONG_WALK_NAME);
    }

    @Test
    @DisplayName("возвращать все книги")
    void getAll() {
        List<Book> allBooks = bookDaoJpa.findAll();
        assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @DisplayName("возвращать все книги по автору")
    void getAllByAuthor() {
        List<Book> actualBooks = bookDaoJpa.findAllByAuthor(new Author(PUSHKIN_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(PUSHKIN_ID)));
    }

    @Test
    @DisplayName("возвращать все книги по жанру")
    void getAllByGenre() {
        List<Book> actualBooks = bookDaoJpa.findAllByGenre(new Genre(HORROR_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getGenre().getId().equals(HORROR_ID)));
    }

    @Test
    @DisplayName("возвращать все книги по автору и жанру")
    void getAllByAuthorAndGenre() {
        List<Book> actualBooks = bookDaoJpa.findAllByAuthorAndGenre(new Author(KING_ID, null), new Genre(HORROR_ID, null));
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(KING_ID)
                        && book.getGenre().getId().equals(HORROR_ID)));
    }

    @Test
    @DisplayName("обновляет данные книги по id")
    void updateById() {
        bookDaoJpa.updateById(LONG_WALK_ID, "testName", new Author(3L, null), new Genre(2L, null));
        Optional<Book> actualBook = bookDaoJpa.findById(LONG_WALK_ID);
        assertAll(() -> assertThat(actualBook).isPresent(),
                () -> assertThat(actualBook.get().getName()).isEqualTo("testName"),
                () -> assertThat(actualBook.get().getAuthor().getId()).isEqualTo(3L),
                () -> assertThat(actualBook.get().getGenre().getId()).isEqualTo(2L));
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void deleteById() {
        bookDaoJpa.deleteById(LONG_WALK_ID);
        List<Book> allBooks = bookDaoJpa.findAll();
        assertAll(() -> assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT - 1),
                () -> assertThat(allBooks.stream()).noneMatch(book -> book.getId().equals(LONG_WALK_ID)));
    }
}