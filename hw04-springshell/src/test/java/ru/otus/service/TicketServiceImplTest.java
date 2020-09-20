package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.dao.TicketDao;

import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("класс TicketServiceImpl")
class TicketServiceImplTest {

    @Configuration
    @Import(TicketServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private TicketDao ticketDao;

    @Autowired
    private TicketService ticketService;

    @Test
    @DisplayName("корректно вызывает ticketDao")
    void getTicket() {
        ticketService.getTicket();
        verify(ticketDao).getTicket();
    }
}