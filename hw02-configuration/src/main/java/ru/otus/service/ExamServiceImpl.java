package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.TicketConfig;
import ru.otus.domain.StudentAnswers;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {

    private final TicketConfig ticketConfig;

    @Override
    public boolean getExamResult(Ticket ticket, StudentAnswers studentAnswers) {
        long countCorrectStudentAnswers = ticket.getQuestions().stream()
                .filter(q -> studentAnswers.getAnswers().get(q).trim().equalsIgnoreCase(q.getCorrectAnswer()))
                .count();
        return countCorrectStudentAnswers >= ticketConfig.getNumberOfCorrectAnswersToPassTheExam();
    }

}
