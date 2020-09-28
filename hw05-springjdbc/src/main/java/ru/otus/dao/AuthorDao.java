package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);

    List<Author> getAll();

}
