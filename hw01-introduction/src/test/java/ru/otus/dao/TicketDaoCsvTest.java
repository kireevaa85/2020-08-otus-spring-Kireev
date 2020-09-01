package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.domain.Ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("класс TicketDaoCsv")
class TicketDaoCsvTest {

    private TicketDao ticketDao;

    @BeforeEach
    void setUp() {
        ticketDao = new TicketDaoCsv();
    }

    @Test
    @DisplayName("корректно возвращает Ticket по имени")
    void getByName() {
        Ticket ticket = ticketDao.getByName("questions.csv");
        assertAll(() -> assertThat(ticket.getQuestions()).hasSize(5),
                () -> assertThat(ticket.getQuestions().get(0).getQuestion()).isEqualTo("How much is the fish?"),
                () -> assertThat(ticket.getQuestions().get(0).getAnswers()).hasSize(4),
                () -> assertThat(ticket.getQuestions().get(0).getAnswers().get(0)).isEqualTo("1$"),

                () -> assertThat(ticket.getQuestions().get(1).getQuestion()).isEqualTo("What is the programmer's favorite number?"),
                () -> assertThat(ticket.getQuestions().get(1).getAnswers()).hasSize(5),
                () -> assertThat(ticket.getQuestions().get(1).getAnswers().get(1)).isEqualTo("32"),

                () -> assertThat(ticket.getQuestions().get(2).getQuestion()).isEqualTo("What is the best programming language?"),
                () -> assertThat(ticket.getQuestions().get(2).getAnswers()).hasSize(6),
                () -> assertThat(ticket.getQuestions().get(2).getAnswers().get(2)).isEqualTo("Pascal"),

                () -> assertThat(ticket.getQuestions().get(3).getQuestion()).isEqualTo("How many bits one byte contains?"),
                () -> assertThat(ticket.getQuestions().get(3).getAnswers()).hasSize(6),
                () -> assertThat(ticket.getQuestions().get(3).getAnswers().get(3)).isEqualTo("8"),

                () -> assertThat(ticket.getQuestions().get(4).getQuestion()).isEqualTo("How many days in a year?"),
                () -> assertThat(ticket.getQuestions().get(4).getAnswers()).hasSize(7),
                () -> assertThat(ticket.getQuestions().get(4).getAnswers().get(6)).isEqualTo("756"));
    }

    @Test
    @DisplayName("бросает TicketNotFoundException при поиске по имени если отсутствует")
    public void getByNameTicketNotFoundException() {
        assertThrows(TicketNotFoundException.class, () -> ticketDao.getByName("fakequestions.csv"));
    }
}