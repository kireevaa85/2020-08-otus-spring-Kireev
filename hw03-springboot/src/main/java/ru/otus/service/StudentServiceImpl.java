package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final ExaminationIOService examinationIOService;

    @Override
    public Student getStudent() {
        examinationIOService.printLocalizedString("examination.greeting1");
        String firstName = examinationIOService.inputAnswer();
        examinationIOService.printLocalizedString("examination.greeting2");
        String secondName = examinationIOService.inputAnswer();
        return new Student(firstName, secondName);
    }

}
