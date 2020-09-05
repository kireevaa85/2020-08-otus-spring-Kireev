package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class TicketPrinterServiceImpl implements PrinterService {

    private final IoStreamService ioStreamService;

    @Override
    public void print(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        ticket.getQuestions().forEach(question -> {
            sb.append("\n\n").append(question.getQuestion());
            question.getAnswers().forEach(s -> sb.append("\n").append(s));
        });
        ioStreamService.outputString(sb.toString());
    }

}
