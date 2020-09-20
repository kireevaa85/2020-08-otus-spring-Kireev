package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("класс TicketDaoCsv")
class TicketDaoCsvTest {

    @Configuration
    @Import(TicketDaoCsv.class)
    static class NestedConfiguration {
    }

    @MockBean
    private TicketConfig ticketConfig;

    @Autowired
    private TicketDao ticketDao;

    @BeforeEach
    void setUp() {
        when(ticketConfig.getResourceName()).thenReturn("/questions_en.csv");
    }

    @Test
    @DisplayName("корректно возвращает Ticket")
    void getTicket() {
        Ticket ticket = ticketDao.getTicket();

        Collection<Executable> executables = new ArrayList<>();
        executables.add(() -> assertThat(ticket.getQuestions()).hasSize(5));
        executables.addAll(executables(ticket, 0, "questions.q1.text", 4, 0, "questions.q1.answer1", "questions.q1.correctAnswer"));
        executables.addAll(executables(ticket, 1, "questions.q2.text", 5, 1, "questions.q2.answer2", "questions.q2.correctAnswer"));
        executables.addAll(executables(ticket, 2, "questions.q3.text", 6, 2, "questions.q3.answer3", "questions.q3.correctAnswer"));
        executables.addAll(executables(ticket, 3, "questions.q4.text", 6, 3, "questions.q4.answer4", "questions.q4.correctAnswer"));
        executables.addAll(executables(ticket, 4, "questions.q5.text", 7, 6, "questions.q5.answer7", "questions.q5.correctAnswer"));

        assertAll(executables);
    }

    private Collection<Executable> executables(Ticket ticket, int index, String question, int answerSize, int indexAnswer, String answer, String correctAnswer) {
        return List.of(() -> assertThat(ticket.getQuestions().get(index).getQuestion()).isEqualTo(question),
                () -> assertThat(ticket.getQuestions().get(index).getAnswers()).hasSize(answerSize),
                () -> assertThat(ticket.getQuestions().get(index).getAnswers().get(indexAnswer)).isEqualTo(answer),
                () -> assertThat(ticket.getQuestions().get(index).getCorrectAnswer()).isEqualTo(correctAnswer));
    }

    @Test
    @DisplayName("бросает TicketNotFoundException если отсутствует")
    public void getTicketTicketNotFoundException() {
        when(ticketConfig.getResourceName()).thenReturn("/fakeQuestions_en.csv");
        assertThrows(TicketNotFoundException.class, () -> ticketDao.getTicket());
    }
}