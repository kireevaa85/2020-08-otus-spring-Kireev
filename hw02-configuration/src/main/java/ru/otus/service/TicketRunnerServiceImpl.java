package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.domain.StudentAnswers;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class TicketRunnerServiceImpl implements RunnerService {

    private final TicketService ticketService;
    private final TicketIOService ticketIOService;
    private final ExamService examService;
    private final StudentService studentService;

    @Override
    public void run() {
        Student student = studentService.getStudent();
        ticketIOService.printString("Ok, " + student.getFirstName() + " " + student.getSecondName() + "!!! Starting examination, read the question and write the answer option.");
        Ticket ticket = ticketService.getTicket();
        boolean examResult = examService.getExamResult(ticket, getStudentAnswers(ticket));
        ticketIOService.printString(examResult ? "\nCongratulations, " + student.getFirstName() + " " + student.getSecondName() + "! You passed the exam!"
                : "\nI'm sorry, " + student.getFirstName() + " " + student.getSecondName() + ". But you didn't pass the exam.");
    }

    private StudentAnswers getStudentAnswers(Ticket ticket) {
        StudentAnswers studentAnswers = new StudentAnswers();
        ticket.getQuestions().forEach(question -> {
            ticketIOService.printQuestion(question);
            studentAnswers.getAnswers().put(question, ticketIOService.inputAnswer());
        });
        return studentAnswers;
    }

}
