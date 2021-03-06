package ru.otus.dao;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookDao {

    int count();

    Long insert(Book book);

    Book getById(Long id);

    List<Book> getAll();

    List<Book> getAllByAuthor(Author author);

    List<Book> getAllByGenre(Genre genre);

    List<Book> getAllByAuthorAndGenre(Author author, Genre genre);

    int updateById(Long id, String name, Long authorId, Long genreId);

    void deleteById(Long id);

}
