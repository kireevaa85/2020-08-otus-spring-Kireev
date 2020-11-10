package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

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
    public long booksCount() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        Optional<Author> authorById = authorRepository.findById(book.getAuthor().getId());
        Optional<Genre> genreById = genreRepository.findById(book.getGenre().getId());
        authorById.ifPresent(book::setAuthor);
        genreById.ifPresent(book::setGenre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookRepository.findAllByAuthor_Id(author.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookRepository.findAllByGenre_Id(genre.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksByAuthorAndGenre(Author author, Genre genre) {
        return bookRepository.findAllByAuthor_IdAndGenre_Id(author.getId(), genre.getId());
    }

    @Override
    @Transactional
    public void updateBookById(String id, String name, Author author, Genre genre) {
        Optional<Author> authorById = authorRepository.findById(author.getId());
        Optional<Genre> genreById = genreRepository.findById(genre.getId());
        Optional<Book> bookById = bookRepository.findById(id);
        bookById.ifPresent(book -> {
            book.setName(name);
            authorById.ifPresentOrElse(book::setAuthor, () -> book.setAuthor(author));
            genreById.ifPresentOrElse(book::setGenre, () -> book.setGenre(genre));
            bookRepository.save(book);
        });
    }

    @Override
    @Transactional
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByBook(Book book) {
        return commentRepository.findAllByBook_Id(book.getId());
    }

    @Override
    @Transactional
    public void updateCommentById(String id, String authorName, String comment) {
        commentRepository.updateById(id, authorName, comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }
}
