package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("класс LocalizedIoServiceImpl")
class LocalizedIoServiceImplTest {

    @Configuration
    @Import(LocalizedIoServiceImpl.class)
    static class NestedConfiguration {
    }

    @MockBean
    private IoService ioService;
    @MockBean
    private LocalizeService localizeService;

    @Autowired
    private LocalizedIoService localizedIoService;

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