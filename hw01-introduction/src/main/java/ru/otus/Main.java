package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.RunnerService;
import ru.otus.service.TicketRunnerServiceImpl;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        RunnerService runnerService = context.getBean(TicketRunnerServiceImpl.class);
        runnerService.run();
        context.close();
    }

}
