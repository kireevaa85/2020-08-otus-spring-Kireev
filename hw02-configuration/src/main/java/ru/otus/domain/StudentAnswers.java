package ru.otus.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class StudentAnswers {

    private final Map<Question, String> answers = new HashMap<>();

}
