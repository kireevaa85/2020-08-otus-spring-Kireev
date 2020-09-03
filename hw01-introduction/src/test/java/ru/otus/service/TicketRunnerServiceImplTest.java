package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс TicketRunnerServiceImpl")
class TicketRunnerServiceImplTest {

    @Mock
    private PrinterService printerService;

    private RunnerService runnerService;

    @BeforeEach
    void setUp() {
        runnerService = new TicketRunnerServiceImpl(printerService);
    }

    @Test
    @DisplayName("корректно вызывает printerService")
    void run() {
        runnerService.run();
        verify(printerService).print();
    }
}