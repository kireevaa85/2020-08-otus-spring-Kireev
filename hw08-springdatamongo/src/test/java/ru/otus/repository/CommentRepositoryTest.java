package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Repository для работы с комментариями должно")
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("добавлять комментарий в БД")
    void save() {
        Comment expectedComment = new Comment(null,
                "Борис",
                "Ну ничего себе рассказ, давайте еще",
                bookRepository.findAll().get(1));
        Comment savedComment = commentRepository.save(expectedComment);
        assertThat(expectedComment.equals(savedComment));
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий по ее id")
    void findById() {
        Comment petr1LongWalkComment = commentRepository.findAll().get(3);
        String petr1LongWalkCommentId = petr1LongWalkComment.getId();
        Optional<Comment> actualComment = commentRepository.findById(petr1LongWalkCommentId);
        assertThat(actualComment).isPresent().get()
                .hasFieldOrPropertyWithValue("id", petr1LongWalkCommentId)
                .hasFieldOrPropertyWithValue("book", petr1LongWalkComment.getBook())
                .hasFieldOrPropertyWithValue("authorName", "Петр 1")
                .hasFieldOrPropertyWithValue("comment", "Ничего не понял, но очень интересно");
    }

    @Test
    @DisplayName("возвращать все комментарии по книге")
    void findAllByBook() {
        Book kristinaBook = bookRepository.findAll().get(0);
        String kristinaBookId = kristinaBook.getId();
        List<Comment> actualComments = commentRepository.findAllByBook_Id(kristinaBookId);
        assertAll(() -> assertThat(actualComments).hasSize(3),
                () -> assertThat(actualComments.stream()).allMatch(c -> c.getBook().getId().equals(kristinaBookId)));
    }

    @Test
    @DisplayName("обновляет данные комментария по id")
    void updateById() {
        Comment petr1LongWalkComment = commentRepository.findAll().get(3);
        String petr1LongWalkCommentId = petr1LongWalkComment.getId();
        commentRepository.updateById(petr1LongWalkCommentId, "testAuthorName", "testComment");
        Optional<Comment> actualComment = commentRepository.findById(petr1LongWalkCommentId);
        assertAll(() -> assertThat(actualComment).get().extracting(Comment::getBook).isEqualTo(petr1LongWalkComment.getBook()),
                () -> assertThat(actualComment).get().extracting(Comment::getAuthorName).isEqualTo("testAuthorName"),
                () -> assertThat(actualComment).get().extracting(Comment::getComment).isEqualTo("testComment"));
    }

}