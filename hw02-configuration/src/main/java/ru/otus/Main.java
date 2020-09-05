package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.RunnerService;
import ru.otus.service.TicketRunnerServiceImpl;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        RunnerService runnerService = context.getBean(TicketRunnerServiceImpl.class);
        runnerService.run();
        context.close();
    }

}
