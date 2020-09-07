package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.TicketDao;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    @Override
    public Ticket getTicket() {
        return ticketDao.getTicket();
    }

}
