package ru.otus.mapper;

import org.mapstruct.Mapper;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto sourceToBookDto(Book source);
    List<BookDto> sourceToListBookDto(List<Book> source);
}
