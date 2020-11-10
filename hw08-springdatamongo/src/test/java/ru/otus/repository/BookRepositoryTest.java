package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Repository для работы с книгами должно")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookRepositoryTest {
    private static final int EXPECTED_BOOKS_COUNT = 5;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("возвращать ожидаемое количество книг в БД")
    void count() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @Order(Integer.MAX_VALUE)
    @DisplayName("обновляет данные книги в БД")
    void saveMerge() {
        Book longWalkBook = bookRepository.findAll().get(1);
        String longWalkId = longWalkBook.getId();
        longWalkBook.setName("testName");
        Author pushkinAuthor = bookRepository.findAll().get(3).getAuthor();
        Genre comedyGenre = bookRepository.findAll().get(3).getGenre();
        longWalkBook.setAuthor(pushkinAuthor);
        longWalkBook.setGenre(comedyGenre);
        bookRepository.save(longWalkBook);
        Optional<Book> actualBook = bookRepository.findById(longWalkId);
        assertAll(() -> assertThat(actualBook).get().extracting(Book::getName).isEqualTo("testName"),
                () -> assertThat(actualBook).get().extracting(Book::getAuthor).extracting(Author::getId).isEqualTo(pushkinAuthor.getId()),
                () -> assertThat(actualBook).get().extracting(Book::getGenre).extracting(Genre::getId).isEqualTo(comedyGenre.getId()));
    }

    @Test
    @Order(Integer.MAX_VALUE - 1)
    @DisplayName("добавлять книгу в БД")
    void savePersist() {
        Book expectedBook = new Book(null,
                "Spring in Action",
                bookRepository.findAll().get(0).getAuthor(),
                bookRepository.findAll().get(0).getGenre());
        Book savedBook = bookRepository.save(expectedBook);
        assertThat(expectedBook.equals(savedBook));
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу по ее id")
    void findById() {
        Book expectedBook = bookRepository.findAll().get(0);
        String expectedBookId = expectedBook.getId();
        Optional<Book> actualBook = bookRepository.findById(expectedBookId);
        assertThat(actualBook).isPresent().get()
                .hasFieldOrPropertyWithValue("id", expectedBookId)
                .hasFieldOrPropertyWithValue("name", expectedBook.getName());
    }

    @Test
    @DisplayName("возвращать все книги")
    void findAll() {
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT);
    }

    @Test
    @DisplayName("возвращать все книги по автору")
    void findAllByAuthor() {
        String pushkinId = bookRepository.findAll().get(3).getAuthor().getId();
        List<Book> actualBooks = bookRepository.findAllByAuthor_Id(pushkinId);
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(pushkinId)));
    }

    @Test
    @DisplayName("возвращать все книги по жанру")
    void findAllByGenre() {
        String horrorId = bookRepository.findAll().get(0).getGenre().getId();
        List<Book> actualBooks = bookRepository.findAllByGenre_Id(horrorId);
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getGenre().getId().equals(horrorId)));
    }

    @Test
    @DisplayName("возвращать все книги по автору и жанру")
    void findAllByAuthorAndGenre() {
        String kingId = bookRepository.findAll().get(0).getAuthor().getId();
        String horrorId = bookRepository.findAll().get(0).getGenre().getId();
        List<Book> actualBooks = bookRepository.findAllByAuthor_IdAndGenre_Id(kingId, horrorId);
        assertAll(() -> assertThat(actualBooks).hasSize(2),
                () -> assertThat(actualBooks.stream()).allMatch(book -> book.getAuthor().getId().equals(kingId)
                        && book.getGenre().getId().equals(horrorId)));
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void deleteById() {
        String longWalkId = bookRepository.findAll().get(1).getId();
        bookRepository.deleteById(longWalkId);
        List<Book> allBooks = bookRepository.findAll();
        assertAll(() -> assertThat(allBooks).hasSize(EXPECTED_BOOKS_COUNT - 1),
                () -> assertThat(allBooks.stream()).noneMatch(book -> book.getId().equals(longWalkId)));
    }

}