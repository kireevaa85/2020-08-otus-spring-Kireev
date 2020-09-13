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
    private final LocalizeService localizeService;

    @Override
    public void run() {
        Student student = studentService.getStudent();
        final String fullName = student.getFirstName() + " " + student.getSecondName();
        examinationIOService.printString(localizeService.localized("examination.greeting3", new String[]{fullName}));
        Ticket ticket = ticketService.getTicket();
        boolean examResult = examService.getExamResult(ticket, getStudentAnswers(ticket));
        examinationIOService.printString(examResult ? localizeService.localized("examination.done", new String[]{fullName})
                : localizeService.localized("examination.fail", new String[]{fullName}));
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
