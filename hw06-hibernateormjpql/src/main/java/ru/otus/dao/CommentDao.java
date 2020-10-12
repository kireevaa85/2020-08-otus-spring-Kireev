package ru.otus.dao;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);

    List<Comment> findAllByBook(Book book);

    void updateById(Long id, String authorName, String comment);

    void deleteById(Long id);

}
