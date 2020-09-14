package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class ExaminationIOServiceImpl implements ExaminationIOService {

    private final IoService ioService;

    @Override
    public void printString(String str) {
        ioService.outputString(str);
    }

    @Override
    public String inputAnswer() {
        return ioService.inputString();
    }

    @Override
    public void printTicket(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        ticket.getQuestions().forEach(question -> {
            sb.append("\n\n").append(question.getQuestion());
            question.getAnswers().forEach(s -> sb.append("\n").append(s));
        });
        ioService.outputString(sb.toString());
    }

    @Override
    public void printQuestion(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n").append(question.getQuestion());
        question.getAnswers().forEach(s -> sb.append("\n").append(s));
        sb.append("\n");
        ioService.outputString(sb.toString());
    }

}
