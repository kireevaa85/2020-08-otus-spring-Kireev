package ru.otus.mapper.book;

import org.mapstruct.Mapper;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto sourceToAuthorDto(Author source);
    List<AuthorDto> sourceToListAuthorDto(List<Author> source);
}
