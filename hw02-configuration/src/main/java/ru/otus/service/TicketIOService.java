package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

public interface TicketIOService {

    void printString(String str);

    void printTicket(Ticket ticket);

    void printQuestion(Question question);

    String inputAnswer();

}
