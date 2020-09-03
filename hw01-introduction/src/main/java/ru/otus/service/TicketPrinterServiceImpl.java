package ru.otus.service;

import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

@RequiredArgsConstructor
public class TicketPrinterServiceImpl implements PrinterService {

    private final TicketService ticketService;

    private final PrintStream printStream;

    @Override
    public void print() {
        ticketService.getTicket().getQuestions().forEach(question -> {
            printStream.println("\n" + question.getQuestion());
            question.getAnswers().forEach(printStream::println);
        });
    }

}
