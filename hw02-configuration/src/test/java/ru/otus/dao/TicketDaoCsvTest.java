package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс TicketDaoCsv")
class TicketDaoCsvTest {

    @Mock
    private TicketConfig ticketConfig;

    private TicketDao ticketDao;

    @Test
    @DisplayName("корректно возвращает Ticket")
    void getTicket() {
        when(ticketConfig.getResourceName()).thenReturn("questions.csv");
        ticketDao = new TicketDaoCsv(ticketConfig);
        Ticket ticket = ticketDao.getTicket();
        assertAll(() -> assertThat(ticket.getQuestions()).hasSize(5),
                () -> assertThat(ticket.getQuestions().get(0).getQuestion()).isEqualTo("How much is the fish?"),
                () -> assertThat(ticket.getQuestions().get(0).getAnswers()).hasSize(4),
                () -> assertThat(ticket.getQuestions().get(0).getAnswers().get(0)).isEqualTo("1$"),
                () -> assertThat(ticket.getQuestions().get(0).getCorrectAnswer()).isEqualTo("3$"),

                () -> assertThat(ticket.getQuestions().get(1).getQuestion()).isEqualTo("What is the programmer's favorite number?"),
                () -> assertThat(ticket.getQuestions().get(1).getAnswers()).hasSize(5),
                () -> assertThat(ticket.getQuestions().get(1).getAnswers().get(1)).isEqualTo("32"),
                () -> assertThat(ticket.getQuestions().get(1).getCorrectAnswer()).isEqualTo("256"),

                () -> assertThat(ticket.getQuestions().get(2).getQuestion()).isEqualTo("What is the best programming language?"),
                () -> assertThat(ticket.getQuestions().get(2).getAnswers()).hasSize(6),
                () -> assertThat(ticket.getQuestions().get(2).getAnswers().get(2)).isEqualTo("Pascal"),
                () -> assertThat(ticket.getQuestions().get(2).getCorrectAnswer()).isEqualTo("Java"),

                () -> assertThat(ticket.getQuestions().get(3).getQuestion()).isEqualTo("How many bits one byte contains?"),
                () -> assertThat(ticket.getQuestions().get(3).getAnswers()).hasSize(6),
                () -> assertThat(ticket.getQuestions().get(3).getAnswers().get(3)).isEqualTo("8"),
                () -> assertThat(ticket.getQuestions().get(3).getCorrectAnswer()).isEqualTo("8"),

                () -> assertThat(ticket.getQuestions().get(4).getQuestion()).isEqualTo("How many days in a year?"),
                () -> assertThat(ticket.getQuestions().get(4).getAnswers()).hasSize(7),
                () -> assertThat(ticket.getQuestions().get(4).getAnswers().get(6)).isEqualTo("756"),
                () -> assertThat(ticket.getQuestions().get(4).getCorrectAnswer()).isEqualTo("365"));
    }

    @Test
    @DisplayName("бросает TicketNotFoundException если отсутствует")
    public void getTicketTicketNotFoundException() {
        when(ticketConfig.getResourceName()).thenReturn("fakeQuestions.csv");
        ticketDao = new TicketDaoCsv(ticketConfig);
        assertThrows(TicketNotFoundException.class, () -> ticketDao.getTicket());
    }
}