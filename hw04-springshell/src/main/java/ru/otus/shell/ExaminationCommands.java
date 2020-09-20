package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.RunnerService;

@RequiredArgsConstructor
@ShellComponent
public class ExaminationCommands {

    private final RunnerService runnerService;

    @ShellMethod(value = "Examination runner command", key = {"r", "run"})
    public void run() {
        runnerService.run();
    }

}
