package ru.otus.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MapperImpl implements Mapper {
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final CommentMapper commentMapper;

    @Override
    public BookDto sourceToBookDto(Book source) {
        return bookMapper.sourceToBookDto(source);
    }

    @Override
    public List<BookDto> sourceToListBookDto(List<Book> source) {
        return bookMapper.sourceToListBookDto(source);
    }

    @Override
    public AuthorDto sourceToAuthorDto(Author source) {
        return authorMapper.sourceToAuthorDto(source);
    }

    @Override
    public List<AuthorDto> sourceToListAuthorDto(List<Author> source) {
        return authorMapper.sourceToListAuthorDto(source);
    }

    @Override
    public GenreDto sourceToGenreDto(Genre source) {
        return genreMapper.sourceToGenreDto(source);
    }

    @Override
    public List<GenreDto> sourceToListGenreDto(List<Genre> source) {
        return genreMapper.sourceToListGenreDto(source);
    }

    @Override
    public CommentDto sourceToCommentDto(Comment source) {
        return commentMapper.sourceToCommentDto(source);
    }

    @Override
    public List<CommentDto> sourceToListCommentDto(List<Comment> source) {
        return commentMapper.sourceToListCommentDto(source);
    }

}
