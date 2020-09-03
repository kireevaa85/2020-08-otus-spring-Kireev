package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.TicketDao;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    @Override
    public Ticket getTicket() {
        return ticketDao.get();
    }

}
