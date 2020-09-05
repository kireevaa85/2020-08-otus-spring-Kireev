package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Question;
import ru.otus.domain.Ticket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class TicketDaoCsv implements TicketDao {

    private final String resourceName;

    @Override
    public Ticket getTicket() throws TicketNotFoundException {
        try (InputStream i = TicketDaoCsv.class.getResourceAsStream("/" + resourceName);
             BufferedReader r = new BufferedReader(new InputStreamReader(i))) {
            List<Question> questions = new ArrayList<>();
            String l;
            while ((l = r.readLine()) != null) {
                if (l.isEmpty()) {
                    continue;
                }
                List<String> elements = Arrays.asList(l.split(";"));
                questions.add(new Question(elements.get(0), elements.subList(1, elements.size())));
            }
            return new Ticket(questions);
        } catch (Exception e) {
            throw new TicketNotFoundException("Ticket with name " + resourceName + " not found");
        }
    }
}
