package ru.otus.mapper.book;

import org.mapstruct.Mapper;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto sourceToCommentDto(Comment source);
    List<CommentDto> sourceToListCommentDto(List<Comment> source);
}
