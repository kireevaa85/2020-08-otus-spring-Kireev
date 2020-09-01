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
class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    private String name;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        name = "questions.csv";
        ticketService = new TicketServiceImpl(ticketDao, name);
    }

    @Test
    void get() {
        ticketService.get();
        verify(ticketDao).getByName(name);
    }
}