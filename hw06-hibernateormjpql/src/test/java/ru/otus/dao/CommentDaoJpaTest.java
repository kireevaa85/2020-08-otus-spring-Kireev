package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(CommentDaoJpa.class)
@DisplayName("Dao для работы с комментариями должно")
class CommentDaoJpaTest {
    private static final long EXPECTED_NEW_ID = 9;
    private static final long PETR1_LONG_WALK_COMMENT_ID = 4;
    private static final long LONG_WALK_BOOK_ID = 2;
    private static final long KRISTINA_BOOK_ID = 1;
    private static final int EXPECTED_COMMENTS_FOR_LONG_WALK_COUNT = 2;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentDaoJpa commentDaoJpa;

    @Test
    @DisplayName("добавлять комментарий в БД")
    void save() {
        Comment expectedComment = new Comment(null,
                2L,
                "Борис",
                "Ну ничего себе рассказ, давайте еще");
        Comment savedComment = commentDaoJpa.save(expectedComment);
        Optional<Comment> actualComment = commentDaoJpa.findById(EXPECTED_NEW_ID);
        assertAll(() -> assertThat(savedComment.getId()).isEqualTo(EXPECTED_NEW_ID),
                () -> assertThat(actualComment).isPresent().get()
                        .isEqualTo(expectedComment));
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий по ее id")
    void findById() {
        Optional<Comment> actualComment = commentDaoJpa.findById(PETR1_LONG_WALK_COMMENT_ID);
        assertThat(actualComment).isPresent().get()
                .hasFieldOrPropertyWithValue("id", PETR1_LONG_WALK_COMMENT_ID)
                .hasFieldOrPropertyWithValue("bookId", LONG_WALK_BOOK_ID)
                .hasFieldOrPropertyWithValue("authorName", "Петр 1")
                .hasFieldOrPropertyWithValue("comment", "Ничего не понял, но очень интересно");
    }

    @Test
    @DisplayName("возвращать все комментарии по книге")
    void findAllByBookId() {
        List<Comment> actualComments = commentDaoJpa.findAllByBookId(KRISTINA_BOOK_ID);
        assertAll(() -> assertThat(actualComments).hasSize(3),
                () -> assertThat(actualComments.stream()).allMatch(c -> c.getBookId().equals(KRISTINA_BOOK_ID)));
    }

    @Test
    @DisplayName("обновляет данные комментария по id")
    void updateById() {
        commentDaoJpa.updateById(PETR1_LONG_WALK_COMMENT_ID, "testAuthorName", "testComment");
        Optional<Comment> actualComment = commentDaoJpa.findById(PETR1_LONG_WALK_COMMENT_ID);
        assertAll(() -> assertThat(actualComment).isPresent(),
                () -> assertThat(actualComment.get().getBookId()).isEqualTo(LONG_WALK_BOOK_ID),
                () -> assertThat(actualComment.get().getAuthorName()).isEqualTo("testAuthorName"),
                () -> assertThat(actualComment.get().getComment()).isEqualTo("testComment"));
    }

    @Test
    @DisplayName("удаляет комментарий по id")
    void deleteById() {
        commentDaoJpa.deleteById(PETR1_LONG_WALK_COMMENT_ID);
        List<Comment> allComments = commentDaoJpa.findAllByBookId(LONG_WALK_BOOK_ID);
        assertAll(() -> assertThat(allComments).hasSize(EXPECTED_COMMENTS_FOR_LONG_WALK_COUNT - 1),
                () -> assertThat(allComments.stream()).noneMatch(c -> c.getId().equals(PETR1_LONG_WALK_COMMENT_ID)));
    }
}