package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketRunnerServiceImpl implements RunnerService {

    private final TicketService ticketService;

    private final PrinterService printerService;

    @Override
    public void run() {
        printerService.print(ticketService.getTicket());
    }

}
