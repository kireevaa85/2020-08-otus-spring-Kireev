package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.config.TicketConfig;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс IoServiceImplTest")
class IoServiceImplTest {

    @Mock
    private TicketConfig ticketConfig;

    private IoService ioService;

    @BeforeEach
    void setUp() {
        ioService = new IoServiceImpl(ticketConfig);
    }

    @Test
    @DisplayName("корректно вызывает printStream")
    void outputString() {
        String str = "test string";
        when(ticketConfig.getPrintStream()).thenReturn(System.out);
        ioService.outputString(str);
        verify(ticketConfig).getPrintStream();
    }

}