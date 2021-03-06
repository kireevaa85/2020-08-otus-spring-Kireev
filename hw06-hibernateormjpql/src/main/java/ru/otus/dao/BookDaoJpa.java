package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@RequiredArgsConstructor
@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.author = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByAuthorAndGenre(Author author, Genre genre) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.author = :author and b.genre = :genre", Book.class);
        query.setParameter("author", author);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public void updateById(Long id, String name, Author author, Genre genre) {
        Query query = em.createQuery("update Book b set b.name = :name, b.author = :author, b.genre = :genre where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("author", author);
        query.setParameter("genre", genre);
        query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
