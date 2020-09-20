package ru.otus.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.service.RunnerService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("класс ExaminationCommands")
class ExaminationCommandsTest {

    @MockBean
    private RunnerService runnerService;

    @Autowired
    private Shell shell;

    private static final String COMMAND_RUN = "run";

    @Test
    @DisplayName("корректно вызывает runnerService")
    void run() {
        shell.evaluate(() -> COMMAND_RUN);
        verify(runnerService, times(1)).run();
    }
}