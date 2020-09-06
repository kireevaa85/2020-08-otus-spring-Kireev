package ru.otus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Getter
@PropertySource("classpath:application.properties")
@Configuration
public class TicketConfig {

    @Value("${exam.ticket.resourceName}")
    private String resourceName;

    @Value("${exam.numberOfCorrectAnswersToPassTheExam}")
    private int numberOfCorrectAnswersToPassTheExam;

}
