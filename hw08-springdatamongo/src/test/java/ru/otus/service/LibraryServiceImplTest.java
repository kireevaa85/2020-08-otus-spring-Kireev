package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Сервис библиотеки должен")
class LibraryServiceImplTest {

    @Configuration
    @Import(LibraryServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private LibraryService libraryService;

    @Test
    @DisplayName("корректно вызывать authorRepository")
    void getAllAuthors() {
        libraryService.getAllAuthors();
        verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("корректно вызывать genreRepository")
    void getAllGenres() {
        libraryService.getAllGenres();
        verify(genreRepository).findAll();
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.count")
    void booksCount() {
        libraryService.booksCount();
        verify(bookRepository).count();
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.save")
    void insertBook() {
        Book book = new Book(null, null, new Author("1", "x"), new Genre("1", "x"));
        libraryService.insertBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.findById")
    void getBookById() {
        libraryService.getBookById("1");
        verify(bookRepository).findById("1");
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.findAll")
    void getAllBooks() {
        libraryService.getAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.findAllByAuthor")
    void getAllBooksByAuthor() {
        Author author = new Author(null, null);
        libraryService.getAllBooksByAuthor(author);
        verify(bookRepository).findAllByAuthor_Id(author.getId());
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.findAllByGenre")
    void getAllBooksByGenre() {
        Genre genre = new Genre(null, null);
        libraryService.getAllBooksByGenre(genre);
        verify(bookRepository).findAllByGenre_Id(genre.getId());
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.findAllByAuthorAndGenre")
    void getAllBooksByAuthorAndGenre() {
        Author author = new Author(null, null);
        Genre genre = new Genre(null, null);
        libraryService.getAllBooksByAuthorAndGenre(author, genre);
        verify(bookRepository).findAllByAuthor_IdAndGenre_Id(author.getId(), genre.getId());
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.updateById")
    void updateBookById() {
        Author author = new Author("2", null);
        Genre genre = new Genre("3", null);
        libraryService.updateBookById("1", "newName", author, genre);
        verify(bookRepository).findById("1");
    }

    @Test
    @DisplayName("корректно вызывать bookRepository.deleteById")
    void deleteBooksById() {
        libraryService.deleteBookById("1");
        verify(bookRepository).deleteById("1");
    }

    @Test
    @DisplayName("корректно вызывать commentRepository.save")
    public void insertComment() {
        Comment comment = new Comment("1", null, null, null);
        libraryService.insertComment(comment);
        verify(commentRepository).save(comment);
    }

    @Test
    @DisplayName("корректно вызывать commentRepository.findById")
    public void getCommentById() {
        libraryService.getCommentById("1");
        verify(commentRepository).findById("1");
    }

    @Test
    @DisplayName("корректно вызывать commentRepository.findAllByBook")
    public void getAllCommentsByBook() {
        Book book = new Book("1", null, null, null);
        libraryService.getAllCommentsByBook(book);
        verify(commentRepository).findAllByBook_Id(book.getId());
    }

    @Test
    @DisplayName("корректно вызывать commentRepository.updateById")
    public void updateCommentById() {
        libraryService.updateCommentById("1", null, null);
        verify(commentRepository).updateById("1", null, null);
    }

    @Test
    @DisplayName("корректно вызывать commentRepository.deleteById")
    public void deleteCommentById() {
        libraryService.deleteCommentById("1");
        verify(commentRepository).deleteById("1");
    }
}