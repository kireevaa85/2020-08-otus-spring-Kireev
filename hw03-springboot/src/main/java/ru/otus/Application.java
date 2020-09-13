package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.config.AppConfig;
import ru.otus.config.TicketConfig;
import ru.otus.service.RunnerService;

@EnableConfigurationProperties({TicketConfig.class, AppConfig.class})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
			RunnerService runnerService = context.getBean(RunnerService.class);
			runnerService.run();
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
