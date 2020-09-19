package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.TicketDao;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс TicketServiceImpl")
class TicketServiceImplTest {

    @Mock
    private TicketDao ticketDao;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketServiceImpl(ticketDao);
    }

    @Test
    @DisplayName("корректно вызывает ticketDao")
    void getTicket() {
        ticketService.getTicket();
        verify(ticketDao).getTicket();
    }
}