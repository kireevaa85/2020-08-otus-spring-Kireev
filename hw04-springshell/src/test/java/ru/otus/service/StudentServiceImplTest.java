package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс StudentServiceImpl")
class StudentServiceImplTest {

    @Mock
    private ExaminationIOService examinationIOService;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(examinationIOService);
    }

    @Test
    @DisplayName("корректно вызывает inputAnswer")
    void getStudent() {
        studentService.getStudent();
        assertAll(() -> verify(examinationIOService, times(2)).inputAnswer(),
                () -> verify(examinationIOService, times(2)).printLocalizedString(anyString()));
    }
}