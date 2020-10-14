package ru.otus.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph("Book.allAttributes")
    List<Book> findAll();

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByAuthor(Author author);

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByGenre(Genre genre);

    @EntityGraph("Book.allAttributes")
    List<Book> findAllByAuthorAndGenre(Author author, Genre genre);

}
