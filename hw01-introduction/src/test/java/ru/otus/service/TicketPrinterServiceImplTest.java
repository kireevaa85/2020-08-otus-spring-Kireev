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
@DisplayName("класс TicketPrinterServiceImpl")
class TicketPrinterServiceImplTest {

    @Mock
    private IoStreamService ioStreamService;

    private PrinterService printerService;

    @BeforeEach
    void setUp() {
        printerService = new TicketPrinterServiceImpl(ioStreamService);
    }

    @Test
    @DisplayName("корректно вызывает ticketService")
    void print() {
        printerService.print(new Ticket(List.of(
                new Question("q1", Arrays.asList("answ1", "answ2", "answ3")),
                new Question("q2", List.of("answ4", "answ5", "answ6", "answ7")),
                new Question("q3", List.of("answ8", "answ9", "answ10", "answ11", "answ12")))));
        verify(ioStreamService).outputString(anyString());
    }

}