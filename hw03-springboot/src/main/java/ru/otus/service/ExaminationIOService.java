package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

public interface ExaminationIOService {

    void printLocalizedString(String code, Object... args);

    String inputAnswer();

    void printTicket(Ticket ticket);

    void printQuestion(Question question);

}
