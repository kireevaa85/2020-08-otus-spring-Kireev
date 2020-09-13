package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.config.AppConfig;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс LocalizeServiceImpl")
class LocalizeServiceImplTest {

    @Mock
    private MessageSource messageSource;
    @Mock
    private AppConfig appConfig;

    private LocalizeService localizeService;

    @BeforeEach
    void setUp() {
        localizeService = new LocalizeServiceImpl(messageSource, appConfig);
    }

    @Test
    @DisplayName("корректно вызывает messageSource и appConfig")
    void localized() {
        Locale locale = new Locale("ru_RU");
        when(appConfig.getLocale()).thenReturn(locale);
        String testCode = "testCode";
        localizeService.localized(testCode);
        verify(messageSource).getMessage(eq(testCode), isNull(), eq(locale));
    }

}