package ru.otus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties("exam.ticket")
public class TicketConfig {

    private String resourceName;

    private int numberOfCorrectAnswersToPassTheExam;

}
