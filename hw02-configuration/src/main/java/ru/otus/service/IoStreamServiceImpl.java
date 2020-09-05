package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.TicketConfig;

@RequiredArgsConstructor
@Service
public class IoStreamServiceImpl implements IoStreamService {

    private final TicketConfig ticketConfig;

    @Override
    public void outputString(String str) {
        ticketConfig.getPrintStream().print(str);
    }

}
