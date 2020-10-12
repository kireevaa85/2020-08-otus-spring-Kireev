package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b join fetch b.author join fetch b.genre where b.author = :author")
    List<Book> findAllByAuthor(Author author);

    @Query("select b from Book b join fetch b.author join fetch b.genre where b.genre = :genre")
    List<Book> findAllByGenre(Genre genre);

    @Query("select b from Book b join fetch b.author join fetch b.genre where b.author = :author and b.genre = :genre")
    List<Book> findAllByAuthorAndGenre(Author author, Genre genre);

    @Modifying
    @Query("update Book b set b.name = :name, b.author = :author, b.genre = :genre where b.id = :id")
    void updateById(@Param("id") Long id, @Param("name") String name, @Param("author") Author author, @Param("genre") Genre genre);

}
