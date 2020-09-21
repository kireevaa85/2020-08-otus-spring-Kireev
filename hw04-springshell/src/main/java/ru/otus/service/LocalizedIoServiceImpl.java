package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalizedIoServiceImpl implements LocalizedIoService {

    private final IoService ioService;
    private final LocalizeService localizeService;

    @Override
    public void outputLocalizedString(String code, Object... args) {
        ioService.outputString(localizeService.localized(code, args));
    }

}
