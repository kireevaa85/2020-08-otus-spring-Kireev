package ru.otus.service;

import ru.otus.domain.StudentAnswers;
import ru.otus.domain.Ticket;

public interface ExamService {

    boolean getExamResult(Ticket ticket, StudentAnswers studentAnswers);

}
