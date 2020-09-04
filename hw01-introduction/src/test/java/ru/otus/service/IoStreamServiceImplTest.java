package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс IoStreamServiceImplTest")
class IoStreamServiceImplTest {

    @Mock
    private PrintStream printStream;

    private IoStreamService ioStreamService;

    @BeforeEach
    void setUp() {
        ioStreamService = new IoStreamServiceImpl(printStream);
    }

    @Test
    @DisplayName("корректно вызывает printStream")
    void outputString() {
        String str = "test string";
        ioStreamService.outputString(str);
        verify(printStream).print(str);
    }

}