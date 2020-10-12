package ru.otus.dao;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Long count();

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByGenre(Genre genre);

    List<Book> findAllByAuthorAndGenre(Author author, Genre genre);

    void updateById(Long id, String name, Author author, Genre genre);

    void deleteById(Long id);

}
