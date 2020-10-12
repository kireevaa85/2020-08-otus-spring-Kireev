package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Long booksCount() {
        return bookDao.count();
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre) {
        return bookDao.findAllByAuthorAndGenre(author, genre);
    }

    @Override
    @Transactional
    public void updateBookById(Long id, String name, Author author, Genre genre) {
        bookDao.updateById(id, name, author, genre);
    }

    @Override
    @Transactional
    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public Comment insertComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByBook(Book book) {
        return commentDao.findAllByBook(book);
    }

    @Override
    @Transactional
    public void updateCommentById(Long id, String authorName, String comment) {
        commentDao.updateById(id, authorName, comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        commentDao.deleteById(id);
    }
}
