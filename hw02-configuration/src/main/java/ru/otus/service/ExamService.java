package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.util.Map;

public interface ExamService {

    boolean getExamResult(Ticket ticket, Map<Question, String> studentAnswers);

}
