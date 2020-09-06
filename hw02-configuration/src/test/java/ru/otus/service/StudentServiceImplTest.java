package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс StudentServiceImpl")
class StudentServiceImplTest {

    @Mock
    private TicketIOService ticketIOService;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(ticketIOService);
    }

    @Test
    @DisplayName("корректно вызывает inputAnswer")
    void getStudent() {
        studentService.getStudent();
        verify(ticketIOService, times(2)).inputAnswer();
    }
}