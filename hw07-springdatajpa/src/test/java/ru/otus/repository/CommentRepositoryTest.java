package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@DisplayName("Repository для работы с комментариями должно")
class CommentRepositoryTest {
    private static final long EXPECTED_NEW_ID = 9;
    private static final long PETR1_LONG_WALK_COMMENT_ID = 4;
    private static final long LONG_WALK_BOOK_ID = 2;
    private static final long KRISTINA_BOOK_ID = 1;
    private static final int EXPECTED_COMMENTS_FOR_LONG_WALK_COUNT = 2;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("добавлять комментарий в БД")
    void save() {
        Comment expectedComment = new Comment(null,
                em.find(Book.class, 2L),
                "Борис",
                "Ну ничего себе рассказ, давайте еще");
        Comment savedComment = commentRepository.save(expectedComment);
        Optional<Comment> actualComment = commentRepository.findById(EXPECTED_NEW_ID);
        assertAll(() -> assertThat(savedComment.getId()).isEqualTo(EXPECTED_NEW_ID),
                () -> assertThat(actualComment).isPresent().get()
                        .isEqualTo(expectedComment));
    }

    @Test
    @DisplayName("возвращать ожидаемый комментарий по ее id")
    void findById() {
        Optional<Comment> actualComment = commentRepository.findById(PETR1_LONG_WALK_COMMENT_ID);
        assertThat(actualComment).isPresent().get()
                .hasFieldOrPropertyWithValue("id", PETR1_LONG_WALK_COMMENT_ID)
                .hasFieldOrPropertyWithValue("book", em.find(Book.class, LONG_WALK_BOOK_ID))
                .hasFieldOrPropertyWithValue("authorName", "Петр 1")
                .hasFieldOrPropertyWithValue("comment", "Ничего не понял, но очень интересно");
    }

    @Test
    @DisplayName("возвращать все комментарии по книге")
    void findAllByBook() {
        List<Comment> actualComments = commentRepository.findAllByBook(em.find(Book.class, KRISTINA_BOOK_ID));
        assertAll(() -> assertThat(actualComments).hasSize(3),
                () -> assertThat(actualComments.stream()).allMatch(c -> c.getBook().getId().equals(KRISTINA_BOOK_ID)));
    }

    @Test
    @DisplayName("обновляет данные комментария по id")
    void updateById() {
        commentRepository.updateById(PETR1_LONG_WALK_COMMENT_ID, "testAuthorName", "testComment");
        Optional<Comment> actualComment = commentRepository.findById(PETR1_LONG_WALK_COMMENT_ID);
        assertAll(() -> assertThat(actualComment).get().extracting(Comment::getBook).isEqualTo(em.find(Book.class, LONG_WALK_BOOK_ID)),
                () -> assertThat(actualComment).get().extracting(Comment::getAuthorName).isEqualTo("testAuthorName"),
                () -> assertThat(actualComment).get().extracting(Comment::getComment).isEqualTo("testComment"));
    }

    @Test
    @DisplayName("удаляет комментарий по id")
    void deleteById() {
        commentRepository.deleteById(PETR1_LONG_WALK_COMMENT_ID);
        List<Comment> allComments = commentRepository.findAllByBook(em.find(Book.class, LONG_WALK_BOOK_ID));
        assertAll(() -> assertThat(allComments).hasSize(EXPECTED_COMMENTS_FOR_LONG_WALK_COUNT - 1),
                () -> assertThat(allComments.stream()).noneMatch(c -> c.getId().equals(PETR1_LONG_WALK_COMMENT_ID)));
    }

}