package ru.otus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;


@Getter
@PropertySource("classpath:application.properties")
@Configuration
public class TicketConfig {

    @Value("${ticket.dao.resourceName}")
    private String resourceName;

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

}
