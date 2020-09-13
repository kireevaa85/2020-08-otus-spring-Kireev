package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppConfig;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalizeServiceImpl implements LocalizeService {

    private final MessageSource messageSource;
    private final AppConfig appConfig;

    @Override
    public String localized(String code, Object[] args) {
        return messageSource.getMessage(code, args, appConfig.getLocale());
    }

    @Override
    public String localized(String code) {
        return localized(code, null);
    }

    @Override
    public List<String> localized(List<String> codes) {
        return codes.stream()
                .map(this::localized)
                .collect(Collectors.toList());
    }

}
