package ru.otus.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TicketRunnerServiceImpl implements RunnerService {

    private final PrinterService printerService;

    @Override
    public void run() {
        printerService.print();
    }

}
