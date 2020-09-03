package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс TicketPrinterServiceImpl")
class TicketPrinterServiceImplTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private PrintStream printStream;

    private PrinterService printerService;

    @BeforeEach
    void setUp() {
        printerService = new TicketPrinterServiceImpl(ticketService, printStream);
    }

    @Test
    @DisplayName("корректно вызывает ticketService")
    void print() {
        when(ticketService.getTicket()).thenReturn(new Ticket(List.of(
                new Question("q1", Arrays.asList("answ1", "answ2", "answ3")),
                new Question("q2", List.of("answ4", "answ5", "answ6", "answ7")),
                new Question("q3", List.of("answ8", "answ9", "answ10", "answ11", "answ12")))));
        printerService.print();
        verify(ticketService).getTicket();
        verify(printStream, times(15)).println(anyString());
    }

}