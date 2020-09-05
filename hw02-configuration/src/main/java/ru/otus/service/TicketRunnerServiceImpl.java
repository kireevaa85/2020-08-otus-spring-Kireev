package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TicketRunnerServiceImpl implements RunnerService {

    private final TicketService ticketService;
    private final TicketIOService ticketIOService;
    private final ExamService examService;

    @Override
    public void run() {
        ticketIOService.printString("Hello, dear student! Let's go!\nPlease, enter your first name: ");
        String firstName = ticketIOService.inputAnswer();
        ticketIOService.printString("And now second name: ");
        String secondName = ticketIOService.inputAnswer();
        ticketIOService.printString("Ok, " + firstName + " " + secondName + "!!! Starting examination, read the question and write the answer option.");

        Ticket ticket = ticketService.getTicket();
        Map<Question, String> studentAnswers = new HashMap<>();
        ticket.getQuestions().forEach(question -> {
            ticketIOService.printQuestion(question);
            studentAnswers.put(question, ticketIOService.inputAnswer());
        });

        boolean examResult = examService.getExamResult(ticket, studentAnswers);
        ticketIOService.printString(examResult ? "\nCongratulations, " + firstName + " " + secondName + "! You passed the exam!"
                : "\nI'm sorry, " + firstName + " " + secondName + ". But you didn't pass the exam.");
    }

}
