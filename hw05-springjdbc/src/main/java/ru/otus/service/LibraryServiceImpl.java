package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }

    @Override
    public int booksCount() {
        return bookDao.count();
    }

    @Override
    public Long insertBook(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.getAllByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.getAllByGenre(genre);
    }

    @Override
    public List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre) {
        return bookDao.getAllByAuthorAndGenre(author, genre);
    }

    @Override
    public int updateBookById(Long id, String name, Author author, Genre genre) {
        return bookDao.updateById(id, name, author.getId(), genre.getId());
    }

    @Override
    public void deleteBooksById(Long id) {
        bookDao.deleteById(id);
    }
}
