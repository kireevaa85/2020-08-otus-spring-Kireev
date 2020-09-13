package ru.otus.dao;

import ru.otus.domain.Ticket;

public interface TicketDao {

    Ticket getTicket() throws TicketNotFoundException;

}
