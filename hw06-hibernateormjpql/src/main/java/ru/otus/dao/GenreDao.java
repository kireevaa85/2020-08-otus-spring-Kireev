package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(Long id);

    List<Genre> getAll();

}
