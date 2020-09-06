package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.TicketConfig;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class IoServiceImpl implements IoService {

    private final TicketConfig ticketConfig;

    @Override
    public void outputString(String str) {
        ticketConfig.getPrintStream().print(str);
    }

    @Override
    public String inputString() {
        Scanner scanner = new Scanner(ticketConfig.getInputStream());
        return scanner.next();
    }

}
