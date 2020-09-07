package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        Collection<Executable> executables = new ArrayList<>();
        executables.add(() -> assertThat(ticket.getQuestions()).hasSize(5));
        executables.addAll(executables(ticket, 0, "How much is the fish?", 4, 0, "1$", "3$"));
        executables.addAll(executables(ticket, 1, "What is the programmer's favorite number?", 5, 1, "32", "256"));
        executables.addAll(executables(ticket, 2, "What is the best programming language?", 6, 2, "Pascal", "Java"));
        executables.addAll(executables(ticket, 3, "How many bits one byte contains?", 6, 3, "8", "8"));
        executables.addAll(executables(ticket, 4, "How many days in a year?", 7, 6, "756", "365"));

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
        when(ticketConfig.getResourceName()).thenReturn("fakeQuestions.csv");
        ticketDao = new TicketDaoCsv(ticketConfig);
        assertThrows(TicketNotFoundException.class, () -> ticketDao.getTicket());
    }
}