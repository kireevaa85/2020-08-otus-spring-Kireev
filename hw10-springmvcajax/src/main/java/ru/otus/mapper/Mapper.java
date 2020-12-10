package ru.otus.mapper;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;

import java.util.List;

public interface Mapper {
    BookDto sourceToBookDto(Book source);
    List<BookDto> sourceToListBookDto(List<Book> source);

    AuthorDto sourceToAuthorDto(Author source);
    List<AuthorDto> sourceToListAuthorDto(List<Author> source);

    GenreDto sourceToGenreDto(Genre source);
    List<GenreDto> sourceToListGenreDto(List<Genre> source);

    CommentDto sourceToCommentDto(Comment source);
    List<CommentDto> sourceToListCommentDto(List<Comment> source);
}
