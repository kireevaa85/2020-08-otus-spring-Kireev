package ru.otus.service;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface LibraryService {

    List<Author> getAllAuthors();

    List<Genre> getAllGenres();

    int booksCount();

    Long insertBook(Book book);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> getAllBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);

    List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre);

    int updateBookById(Long id, String name, Author author, Genre genre);

    void deleteBooksById(Long id);

}
