package ru.otus.util;

import ru.otus.domain.Ticket;

public class TicketHelper {

    public void print(Ticket ticket) {
        ticket.getQuestions().forEach(question -> {
            System.out.println("\n" + question.getQuestion());
            question.getAnswers().forEach(System.out::println);
        });
    }

}
