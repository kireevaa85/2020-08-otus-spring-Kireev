package ru.otus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;


@Getter
@PropertySource("classpath:application.properties")
@Configuration
public class TicketConfig {

    private final PrintStream printStream = System.out;

    @Value("${ticket.dao.resourceName}")
    private String resourceName;

}
