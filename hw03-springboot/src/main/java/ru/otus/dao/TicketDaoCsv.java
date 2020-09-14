package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.config.AppConfig;
import ru.otus.config.TicketConfig;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TicketDaoCsv implements TicketDao {

    private final TicketConfig ticketConfig;
    private final AppConfig appConfig;

    @Override
    public Ticket getTicket() throws TicketNotFoundException {
        final String resourceName = String.format("/%s_%s.%s",
                ticketConfig.getResourceName(), appConfig.getLocale(), ticketConfig.getResourceExtension());
        try (InputStream i = TicketDaoCsv.class.getResourceAsStream(resourceName);
             BufferedReader r = new BufferedReader(new InputStreamReader(i))) {
            List<Question> questions = new ArrayList<>();
            String l;
            while ((l = r.readLine()) != null) {
                if (l.isEmpty()) {
                    continue;
                }
                List<String> elements = Arrays.asList(l.split(";"));
                questions.add(new Question(
                        elements.get(0),
                        elements.get(1),
                        elements.subList(2, elements.size())));
            }
            return new Ticket(questions);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TicketNotFoundException("Ticket with name " + resourceName + " not found");
        }
    }

}
