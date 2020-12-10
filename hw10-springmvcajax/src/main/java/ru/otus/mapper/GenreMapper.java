package ru.otus.mapper;

import org.mapstruct.Mapper;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto sourceToGenreDto(Genre source);
    List<GenreDto> sourceToListGenreDto(List<Genre> source);
}
