package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Student;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final ExaminationIOService examinationIOService;
    private final LocalizeService localizeService;

    @Override
    public Student getStudent() {
        examinationIOService.printString(localizeService.localized("examination.greeting1"));
        String firstName = examinationIOService.inputAnswer();
        examinationIOService.printString(localizeService.localized("examination.greeting2"));
        String secondName = examinationIOService.inputAnswer();
        return new Student(firstName, secondName);
    }

}
