package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс LocalizedIoServiceImpl")
class LocalizedIoServiceImplTest {

    @Mock
    private IoService ioService;
    @Mock
    private LocalizeService localizeService;

    private LocalizedIoService localizedIoService;

    @BeforeEach
    void setUp() {
        localizedIoService = new LocalizedIoServiceImpl(ioService, localizeService);
    }

    @Test
    @DisplayName("корректно вызывает ioService и localizeService")
    void outputLocalizedString() {
        String code = "test.code";
        Object[] args = new String[]{"first", "second"};
        String localizedString = "localizedString";
        when(localizeService.localized(code, args)).thenReturn(localizedString);
        localizedIoService.outputLocalizedString(code, args);
        assertAll(() -> verify(localizeService).localized(code, args),
                () -> verify(ioService).outputString(localizedString));
    }
}