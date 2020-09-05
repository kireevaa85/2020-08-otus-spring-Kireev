package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class TicketIOServiceImpl implements TicketIOService {

    private final IoStreamService ioStreamService;

    @Override
    public void printString(String str) {
        ioStreamService.outputString(str);
    }

    @Override
    public void printTicket(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        ticket.getQuestions().forEach(question -> {
            sb.append("\n\n").append(question.getQuestion());
            question.getAnswers().forEach(s -> sb.append("\n").append(s));
        });
        ioStreamService.outputString(sb.toString());
    }

    @Override
    public void printQuestion(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n").append(question.getQuestion());
        question.getAnswers().forEach(s -> sb.append("\n").append(s));
        sb.append("\n");
        ioStreamService.outputString(sb.toString());
    }

    @Override
    public String inputAnswer() {
        Scanner scanner = new Scanner(ioStreamService.inputString());
        return scanner.next();
    }

}
