package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketRunnerServiceImpl implements RunnerService {

    private final TicketService ticketService;

    private final TicketPrinterService ticketPrinterService;

    @Override
    public void run() {
        ticketPrinterService.print(ticketService.getTicket());
    }

}
