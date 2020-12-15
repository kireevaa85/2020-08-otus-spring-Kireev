package ru.otus.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.NotFoundException;
import ru.otus.dto.CommentDto;
import ru.otus.mapper.Mapper;
import ru.otus.service.LibraryService;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final LibraryService libraryService;
    private final Mapper mapper;

    @GetMapping("/api/comments")
    public List<CommentDto> getComments(@RequestParam String bookId) {
        try {
            return mapper.sourceToListCommentDto(libraryService.getAllCommentsByBook(libraryService.getBookById(bookId).orElseThrow(NotFoundException::new)));
        } catch (NotFoundException e) {
            return Collections.emptyList();
        }
    }

}
