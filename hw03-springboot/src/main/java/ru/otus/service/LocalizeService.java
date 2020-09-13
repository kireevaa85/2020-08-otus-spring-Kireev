package ru.otus.service;

import java.util.List;

public interface LocalizeService {

    String localized(String code, Object[] args);

    String localized(String code);

    List<String> localized(List<String> codes);

}
