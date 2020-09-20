package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Question;
import ru.otus.domain.StudentAnswers;
import ru.otus.domain.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("класс ExamServiceImpl")
class ExamServiceImplTest {

    @Configuration
    @Import(ExamServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private TicketConfig ticketConfig;

    @Autowired
    private ExamService examService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        when(ticketConfig.getNumberOfCorrectAnswersToPassTheExam()).thenReturn(3);
        ticket = new Ticket(List.of(
                new Question("answ1", "q1", Arrays.asList("answ1", "answ2", "answ3")),
                new Question("answ5", "q2", List.of("answ4", "answ5", "answ6", "answ7")),
                new Question("answ12", "q3", List.of("answ8", "answ9", "answ10", "answ11", "answ12")),
                new Question("answ14", "q4", List.of("answ13", "answ14", "answ15", "answ16", "answ17")),
                new Question("answ20", "q5", List.of("answ18", "answ19", "answ20", "answ21", "answ22"))));
    }

    @Test
    @DisplayName("корректно сдает зачет")
    void getExamResultTrue() {
        StudentAnswers studentAnswers = new StudentAnswers();
        Predicate<String> predicate = s -> s.equals("q1") || s.equals("q3") || s.equals("q5");
        ticket.getQuestions().stream()
                .filter(q -> predicate.test(q.getQuestion()))
                .forEach(q -> studentAnswers.putAnswer(q, q.getCorrectAnswer()));
        ticket.getQuestions().stream()
                .filter(q -> predicate.negate().test(q.getQuestion()))
                .forEach(q -> studentAnswers.putAnswer(q, "fake" + q.getCorrectAnswer()));
        assertThat(examService.getExamResult(ticket, studentAnswers)).isTrue();
    }

    @Test
    @DisplayName("корректно не сдает зачет")
    void getExamResultFalse() {
        StudentAnswers studentAnswers = new StudentAnswers();
        Predicate<String> predicate = s -> s.equals("q1") || s.equals("q3");
        ticket.getQuestions().stream()
                .filter(q -> predicate.test(q.getQuestion()))
                .forEach(q -> studentAnswers.putAnswer(q, q.getCorrectAnswer()));
        ticket.getQuestions().stream()
                .filter(q -> predicate.negate().test(q.getQuestion()))
                .forEach(q -> studentAnswers.putAnswer(q, "fake" + q.getCorrectAnswer()));
        assertThat(examService.getExamResult(ticket, studentAnswers)).isFalse();
    }

}