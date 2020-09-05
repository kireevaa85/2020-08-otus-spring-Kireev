package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {

    private final TicketConfig ticketConfig;

    @Override
    public boolean getExamResult(Ticket ticket, Map<Question, String> studentAnswers) {
        long countCorrectStudentAnswers = ticket.getQuestions().stream()
                .filter(q -> studentAnswers.get(q).trim().equalsIgnoreCase(q.getCorrectAnswer()))
                .count();
        return countCorrectStudentAnswers >= ticketConfig.getNumberOfCorrectAnswersToPassTheExam();
    }

}
