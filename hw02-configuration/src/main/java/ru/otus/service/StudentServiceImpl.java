package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final TicketIOService ticketIOService;

    @Override
    public Student getStudent() {
        ticketIOService.printString("Hello, dear student! Let's go!\nPlease, enter your first name: ");
        String firstName = ticketIOService.inputAnswer();
        ticketIOService.printString("And now second name: ");
        String secondName = ticketIOService.inputAnswer();
        return new Student(firstName, secondName);
    }

}
