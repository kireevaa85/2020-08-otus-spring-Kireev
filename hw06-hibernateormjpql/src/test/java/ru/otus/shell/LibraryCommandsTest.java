package ru.otus.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.LibraryService;

import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("класс LibraryCommands")
class LibraryCommandsTest {

    @Autowired
    private Shell shell;

    @MockBean
    private LibraryService libraryService;

    private static final String COMMAND_AUTHORS = "authors";
    private static final String COMMAND_GENRES = "genres";
    private static final String COMMAND_BOOKS_COUNT = "booksCount";
    private static final String COMMAND_INSERT_BOOK = "insertBook";
    private static final String COMMAND_BOOK_BY_ID = "bookById";
    private static final String COMMAND_ALL_BOOKS = "allBooks";
    private static final String COMMAND_BOOKS_BY_AUTHOR_ID = "booksByAuthorId";
    private static final String COMMAND_BOOKS_BY_GENRE_ID = "booksByGenreId";
    private static final String COMMAND_BOOKS_BY_BY_AUTHOR_ID_AND_GENRE_ID = "booksByAuthorIdAndGenreId";
    private static final String COMMAND_UPDATE_BOOK = "updateBook";
    private static final String COMMAND_DELETE_BOOK_BY_ID = "deleteBookById";

    @Test
    @DisplayName("корректно вызывает libraryService.getAllAuthors")
    void authors() {
        shell.evaluate(() -> COMMAND_AUTHORS);
        verify(libraryService).getAllAuthors();
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getAllGenres")
    void genres() {
        shell.evaluate(() -> COMMAND_GENRES);
        verify(libraryService).getAllGenres();
    }

    @Test
    @DisplayName("корректно вызывает libraryService.booksCount()")
    void count() {
        shell.evaluate(() -> COMMAND_BOOKS_COUNT);
        verify(libraryService).booksCount();
    }

    @Test
    @DisplayName("корректно вызывает libraryService.insertBook")
    void insertBook() {
        shell.evaluate(() -> COMMAND_INSERT_BOOK + " bookName 1 1");
        verify(libraryService).insertBook(new Book(null, "bookName", new Author(1L, null), new Genre(1L, null)));
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getBookById")
    void getBookById() {
        shell.evaluate(() -> COMMAND_BOOK_BY_ID + " 1");
        verify(libraryService).getBookById(1L);
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getAllBooks")
    void getAllBooks() {
        shell.evaluate(() -> COMMAND_ALL_BOOKS);
        verify(libraryService).getAllBooks();
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getAllBooksByAuthor")
    void getAllBooksByAuthor() {
        shell.evaluate(() -> COMMAND_BOOKS_BY_AUTHOR_ID + " 1");
        verify(libraryService).getAllBooksByAuthor(new Author(1L, null));
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getAllBooksByGenre")
    void getAllBooksByGenre() {
        shell.evaluate(() -> COMMAND_BOOKS_BY_GENRE_ID + " 1");
        verify(libraryService).getAllBooksByGenre(new Genre(1L, null));
    }

    @Test
    @DisplayName("корректно вызывает libraryService.getAllBooksByAuthorAndGenre")
    void getAllBooksByAuthorAndGenre() {
        shell.evaluate(() -> COMMAND_BOOKS_BY_BY_AUTHOR_ID_AND_GENRE_ID + " 1 1");
        verify(libraryService).getAllBooksByAuthorAndGenre(new Author(1L, null), new Genre(1L, null));
    }

    @Test
    @DisplayName("корректно вызывает libraryService.updateBookById")
    void updateBookById() {
        shell.evaluate(() -> COMMAND_UPDATE_BOOK + " 1 bookName 2 3");
        verify(libraryService).updateBookById(1L, "bookName", new Author(2L, null), new Genre(3L, null));
    }

    @Test
    @DisplayName("корректно вызывает libraryService.deleteBookById")
    void deleteBooksById() {
        shell.evaluate(() -> COMMAND_DELETE_BOOK_BY_ID + " 1");
        verify(libraryService).deleteBookById(1L);
    }
}