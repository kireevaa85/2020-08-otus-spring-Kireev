package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthor_Id(String authorId);

    List<Book> findAllByGenre_Id(String genreId);

    List<Book> findAllByAuthor_IdAndGenre_Id(String authorId, String genreId);

}
