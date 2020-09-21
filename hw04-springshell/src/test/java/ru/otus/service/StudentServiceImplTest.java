package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("класс StudentServiceImpl")
class StudentServiceImplTest {

    @Configuration
    @Import(StudentServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private ExaminationIOService examinationIOService;

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("корректно вызывает inputAnswer")
    void getStudent() {
        studentService.getStudent();
        assertAll(() -> verify(examinationIOService, times(2)).inputAnswer(),
                () -> verify(examinationIOService, times(2)).printLocalizedString(anyString()));
    }
}