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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.singletonMap;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
    }

    @Override
    public Long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into book(name, author_id, genre_id) values (:name, :author_id, :genre_id)", params, kh);
        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    @Override
    public Book getById(Long id) {
        final Map<String, Object> params = singletonMap("id", id);
        return jdbc.queryForObject("select * from book where id = :id", params, new BookMapper(jdbc));
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from book", new BookMapper(jdbc));
    }

    @Override
    public List<Book> getAllByAuthor(Author author) {
        final Map<String, Object> params = singletonMap("author_id", author.getId());
        return jdbc.query("select * from book where author_id = :author_id", params, new BookMapper(jdbc));
    }

    @Override
    public List<Book> getAllByGenre(Genre genre) {
        final Map<String, Object> params = singletonMap("genre_id", genre.getId());
        return jdbc.query("select * from book where genre_id = :genre_id", params, new BookMapper(jdbc));
    }

    @Override
    public List<Book> getAllByAuthorAndGenre(Author author, Genre genre) {
        final Map<String, Object> params = Map.of("author_id", author.getId(),"genre_id", genre.getId());
        return jdbc.query("select * from book where author_id = :author_id and genre_id = :genre_id", params, new BookMapper(jdbc));
    }

    @Override
    public int updateById(Long id, String name, Long authorId, Long genreId) {
        final Map<String, Object> params = Map.of("id", id, "name", name, "author_id", authorId, "genre_id", genreId);
        return jdbc.update("update book set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id", params);
    }

    @Override
    public void deleteById(Long id) {
        final Map<String, Object> params = singletonMap("id", id);
        jdbc.update("delete from book where id = :id", params);
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        private final NamedParameterJdbcOperations jdbc;

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Long id = rs.getLong("id");
            final String name = rs.getString("name");
            final Long authorId = rs.getLong("author_id");
            final Long genreId = rs.getLong("genre_id");
            final Map<String, Object> params = Map.of("author_id", authorId, "genre_id", genreId);
            Map<String, Object> map = jdbc.queryForMap("select a.name as author_name, g.name as genre_name from author a join genre g on a.id = :author_id and g.id = :genre_id", params);
            final Author author = new Author(authorId, (String) map.get("author_name"));
            final Genre genre = new Genre(genreId, (String) map.get("genre_name"));
            return new Book(id, name, author, genre);
        }
    }

}
