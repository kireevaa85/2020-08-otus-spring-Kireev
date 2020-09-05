package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс TicketIOServiceImpl")
class TicketIOServiceImplTest {

    @Mock
    private IoStreamService ioStreamService;

    private TicketIOService ticketIOService;

    @BeforeEach
    void setUp() {
        ticketIOService = new TicketIOServiceImpl(ioStreamService);
    }

    @Test
    @DisplayName("корректно вызывает ioStreamService")
    void printString() {
        String str = "test string";
        ticketIOService.printString(str);
        verify(ioStreamService).outputString(str);
    }

    @Test
    @DisplayName("корректно вызывает ioStreamService")
    void printTicket() {
        ticketIOService.printTicket(new Ticket(List.of(
                new Question("answ1", "q1", Arrays.asList("answ1", "answ2", "answ3")),
                new Question("answ5", "q2", List.of("answ4", "answ5", "answ6", "answ7")),
                new Question("answ12", "q3", List.of("answ8", "answ9", "answ10", "answ11", "answ12")))));
        verify(ioStreamService).outputString(anyString());
    }

    @Test
    @DisplayName("корректно вызывает ioStreamService")
    void printQuestion() {
        ticketIOService.printQuestion(new Question("answ1", "q1", Arrays.asList("answ1", "answ2", "answ3")));
        verify(ioStreamService).outputString(anyString());
    }

}