package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Ticket;
import ru.otus.service.TicketService;
import ru.otus.util.TicketHelper;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TicketService service = context.getBean(TicketService.class);
        Ticket ticket = service.get();
        TicketHelper ticketHelper = context.getBean(TicketHelper.class);
        ticketHelper.print(ticket);
        context.close();
    }

}
