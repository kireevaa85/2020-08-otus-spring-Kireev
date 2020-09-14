package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.domain.StudentAnswers;
import ru.otus.domain.Ticket;

@RequiredArgsConstructor
@Service
public class ExaminationRunnerServiceImpl implements RunnerService {

    private final TicketService ticketService;
    private final ExaminationIOService examinationIOService;
    private final ExamService examService;
    private final StudentService studentService;

    @Override
    public void run() {
        Student student = studentService.getStudent();
        final String fullName = student.getFirstName() + " " + student.getSecondName();
        examinationIOService.printLocalizedString("examination.greeting3", fullName);
        Ticket ticket = ticketService.getTicket();
        boolean examResult = examService.getExamResult(ticket, getStudentAnswers(ticket));
        examinationIOService.printLocalizedString(examResult ? "examination.done" : "examination.fail", fullName);
    }

    private StudentAnswers getStudentAnswers(Ticket ticket) {
        StudentAnswers studentAnswers = new StudentAnswers();
        ticket.getQuestions().forEach(question -> {
            examinationIOService.printQuestion(question);
            studentAnswers.putAnswer(question, examinationIOService.inputAnswer());
        });
        return studentAnswers;
    }

}
